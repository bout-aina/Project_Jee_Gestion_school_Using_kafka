package ma.emsi.dataanalytics_service.controllers;

import ma.emsi.dataanalytics_service.entities.Absence;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Window;
import org.apache.kafka.streams.kstream.Windowed;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreType;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyWindowStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.binder.kafka.streams.InteractiveQueryService;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import javax.ws.rs.core.MediaType;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_NDJSON_VALUE;
import static org.springframework.http.MediaType.TEXT_EVENT_STREAM_VALUE;

@RestController
public class AbsenceController {
    @Autowired
    private StreamBridge streamBridge;
    @Autowired
    private InteractiveQueryService interactiveQueryService;
    @GetMapping("/publish/{topic}/{id}")
    public Absence publish(@PathVariable String topic, @PathVariable Long id ){
        Absence pageEvent=new Absence(id,  new Date(), Math.random()>0.5?1L:2L,Math.random()>0.5?1L:2L,Math.random()>0.5?1L:2L);
        streamBridge.send(topic,pageEvent);
        return pageEvent;
    }
    @GetMapping(path = "/analytics",produces = TEXT_EVENT_STREAM_VALUE)
    public Flux<Map<Long,Long>> analytics(){
        return  Flux.interval(Duration.ofSeconds(1))
                .map(sequence->{
                    Map<Long,Long> LongMap = new HashMap<>();
                    ReadOnlyWindowStore<Long,Long> stats = interactiveQueryService.getQueryableStore("Absence-count", QueryableStoreTypes.windowStore());
                    Instant now = Instant.now();
                    Instant from = now.minusMillis(5000);
                    KeyValueIterator<Windowed<Long>,Long> fetchAll = stats.fetchAll(from,now);

                    while (fetchAll.hasNext()){
                        KeyValue<Windowed<Long>,Long> next = fetchAll.next();
                        LongMap.put(next.key.key(),next.value);
                    }
                    return LongMap;
                }).share();
    }
}
