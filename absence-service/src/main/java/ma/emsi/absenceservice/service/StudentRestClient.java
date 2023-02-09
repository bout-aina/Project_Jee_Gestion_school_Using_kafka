package ma.emsi.absenceservice.service;

import ma.emsi.absenceservice.models.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "STUDENT-SERVICE")
public interface StudentRestClient {
    @GetMapping(path = "/students/{id}")
    Student getStudentById(@PathVariable Long id);

    @GetMapping(path = "/students")
    List<Student> getStudents();
}
