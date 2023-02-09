package ma.emsi.dataanalytics_service;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;

import java.time.Duration;
import java.util.Date;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import ma.emsi.absenceservice.entities.Absence;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class AbsenceService {
    @Bean
    public Consumer<Absence> absenceConsumer(){
        return (input)->{
            System.out.println("**********************");
            System.out.println(input.toString());
            System.out.println("**********************");
        };
    }
    @Bean
    public Supplier<Absence> absenceSupplier(){
        return ()-> new Absence(new Random().nextLong(30),
                new Date(), Math.random()>0.5?1L:2L,
                Math.random()>0.5?1L:2L,Math.random()>0.5?1L:2L,
                null,null,null);
    }
    @Bean
    public Function<Absence,Absence> absenceFunction(){
        return (input)->{
            input.setId(Long.valueOf("id:"+input.getId()));
            input.setIdCourse(1L);
            input.setIdCourse(1L);
            input.setIdProf(1L);
            return input;
        };
    }
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

