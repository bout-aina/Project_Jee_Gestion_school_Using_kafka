package ma.emsi.absencekafkaservice.controller;

import ma.emsi.absencekafkaservice.kafka.AbsenceProducer;
import ma.emsi.absenceservice.entities.Absence;
import ma.emsi.studentservice.entities.Student;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/v1")
public class AbsenceController {
    private AbsenceProducer absenceProducer;

    public AbsenceController(AbsenceProducer absenceProducer) {
        this.absenceProducer = absenceProducer;
    }

    @PostMapping("/absenceskafka")
    public String addabsence()
    {

        Absence absence =  new Absence(null,  new Date(), 1L,1L,1L,null,null,null);

        absenceProducer.sendMessage(absence);
        return  "Absence placed successfully";
    }
}
