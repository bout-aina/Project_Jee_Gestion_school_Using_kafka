package ma.emsi.dataanalytics_service;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;

import java.time.Duration;
import java.util.function.Function;
import ma.emsi.absenceservice.entities.Absence;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class AbsenceService {
@Bean
    public Function<KStream<Long, Absence>, KStream<Long, Long>> KStreamFunction() {
        return (input) -> {
            return input
                    .filter((k, v) -> v.getIdCourse() < 40)
                    .map((k, v) -> new KeyValue<>(v.getId(), 1L))
                    .groupBy((k, v) -> k, Grouped.with(Serdes.Long(), Serdes.Long()))
                    .windowedBy(TimeWindows.of(Duration.ofSeconds(5)))
                    .count(Materialized.as("Absence-count"))
                    .toStream()
                    .map((k,v)->new KeyValue<>(k.key(),v));
        };
    }
}

