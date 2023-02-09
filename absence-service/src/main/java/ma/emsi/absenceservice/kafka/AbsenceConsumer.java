package ma.emsi.absenceservice.kafka;

import ma.emsi.absenceservice.entities.Absence;
import ma.emsi.absenceservice.repos.AbsenceRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class AbsenceConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbsenceConsumer.class);
    private AbsenceRepo absenceRepo;

    public AbsenceConsumer(AbsenceRepo absenceRepo) {
        this.absenceRepo = absenceRepo;
    }

    @KafkaListener(topics = "${spring.kafka.topic.name}",
    groupId = "${spring.kafka.consumer.group-id}")
    public  void  consume(Absence absence){
        LOGGER.info(String.format("good"+absence.toString()));
        absenceRepo.save(absence);
    }
}
