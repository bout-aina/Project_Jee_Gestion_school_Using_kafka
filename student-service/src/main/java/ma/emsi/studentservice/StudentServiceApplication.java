package ma.emsi.studentservice;

import lombok.AllArgsConstructor;
import ma.emsi.studentservice.entities.Student;
import ma.emsi.studentservice.services.StudentService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class StudentServiceApplication implements CommandLineRunner {
    private final StudentService studentService;


    public static void main(String[] args) {
        SpringApplication.run(StudentServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List.of("boutaina","idriss","leila").forEach(name->{
            Student student = new Student();
            student.setName(name);
            student.setEmail(name+"@email.com");
            studentService.createStudent(student);
        });
    }
}
