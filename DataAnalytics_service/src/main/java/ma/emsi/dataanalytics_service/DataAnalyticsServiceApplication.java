package ma.emsi.dataanalytics_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafkaStreams;

@SpringBootApplication

public class DataAnalyticsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataAnalyticsServiceApplication.class, args);
    }

}
