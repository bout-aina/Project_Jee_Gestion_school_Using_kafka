package ma.emsi.professorsservice.controllers;

import lombok.AllArgsConstructor;
import ma.emsi.professorsservice.entities.Professor;
import ma.emsi.professorsservice.services.ProfessorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professors")
@AllArgsConstructor
public class ProfessorController {

    private final ProfessorService professorService;
    @GetMapping
    public List<Professor> findAll() {
        return professorService.findAll();
    }

    @GetMapping("/{id}")
    public Professor findById(@PathVariable Long id) {
        return professorService.findById(id);
    }

    @PostMapping
    public Professor save(@RequestBody Professor professor) {
        return professorService.save(professor);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        professorService.deleteById(id);
    }

}
