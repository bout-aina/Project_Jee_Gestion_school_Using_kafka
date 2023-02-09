package ma.emsi.absenceservice;


import ma.emsi.absenceservice.entities.Absence;
import ma.emsi.absenceservice.repos.AbsenceRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class AbsenceServiceApplication {
    private  AbsenceRepo absenceRepo;



    public static void main(String[] args) {
        SpringApplication.run(AbsenceServiceApplication.class, args);
    }

   @Bean
    CommandLineRunner commandLineRunner(AbsenceRepo absenceRepo) {
        return args -> {
            absenceRepo.save(
                    new Absence(null,  new Date(), 1L,1L,1L,null,null,null));

            absenceRepo.findAll().forEach(p->{
                System.out.println(p.getDateAbsence());
            });

        };
    }
}
