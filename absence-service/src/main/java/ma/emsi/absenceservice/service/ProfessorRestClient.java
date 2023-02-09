package ma.emsi.absenceservice.service;

import ma.emsi.absenceservice.models.Professor;
import ma.emsi.absenceservice.models.Student;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "PROFESSORS-SERVICE")
public interface ProfessorRestClient {

    @GetMapping(path = "/professors/{id}")
    Professor getProfessorById(@PathVariable Long id);

    @GetMapping(path = "/professors")
    List<Professor> getProfessors();
}
