package ma.emsi.absencekafkaservice;

import ma.emsi.absencekafkaservice.kafka.AbsenceProducer;
import ma.emsi.absenceservice.entities.Absence;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.Random;

@SpringBootApplication
public class AbsencekafkaServiceApplication {
    private AbsenceProducer absenceProducer;

    public AbsencekafkaServiceApplication(AbsenceProducer absenceProducer) {
        this.absenceProducer = absenceProducer;
    }

    long leftLimit = 1L;
    long rightLimit = 1L;
    long generatedLong = leftLimit + (long) (Math.random() * (rightLimit - leftLimit));
    public static void main(String[] args) {
        SpringApplication.run(AbsencekafkaServiceApplication.class, args);


    }
    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            for (int i = 0; i < 5; i++) {
                Absence absence =  new Absence(new Random().nextLong(30),  new Date(), Math.random()>0.5?1L:2L,Math.random()>0.5?1L:2L,Math.random()>0.5?1L:2L,null,null,null);
                System.out.println(absence);
                absenceProducer.sendMessage(absence);
            }








        };
    }

}
