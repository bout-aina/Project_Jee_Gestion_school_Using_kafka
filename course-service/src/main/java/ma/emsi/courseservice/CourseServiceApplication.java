package ma.emsi.courseservice;

import lombok.AllArgsConstructor;
import ma.emsi.courseservice.entities.Course;
import ma.emsi.courseservice.services.CourseService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class CourseServiceApplication implements CommandLineRunner {

    private final CourseService courseService;
    public static void main(String[] args) {
        SpringApplication.run(CourseServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        List.of("JEE","NET","DEVOPS").forEach(c->{
            Course course = Course.builder().name(c).description("desc of "+c+" ...").build();
            courseService.save(course);

        });
    }
}
