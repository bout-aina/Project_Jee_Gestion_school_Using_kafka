package ma.emsi.professorsservice;

import lombok.AllArgsConstructor;
import ma.emsi.professorsservice.entities.Professor;
import ma.emsi.professorsservice.services.ProfessorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class ProfessorsServiceApplication  implements CommandLineRunner {
    private final ProfessorService professorService;

    public static void main(String[] args) {
        SpringApplication.run(ProfessorsServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List.of("Mohamed","Tarike","Phelipe").forEach(name->{
            Professor professor = Professor.builder().name(name).department("dept1").build();
            professorService.save(professor);
        });
    }
}
