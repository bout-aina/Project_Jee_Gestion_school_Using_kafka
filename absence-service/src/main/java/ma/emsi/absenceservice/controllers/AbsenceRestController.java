package ma.emsi.absenceservice.controllers;

import ma.emsi.absenceservice.entities.Absence;
import ma.emsi.absenceservice.repos.AbsenceRepo;
import ma.emsi.absenceservice.service.CourseRestClient;
import ma.emsi.absenceservice.service.ProfessorRestClient;
import ma.emsi.absenceservice.service.StudentRestClient;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
public class AbsenceRestController {
    StudentRestClient studentRestClient;
    ProfessorRestClient professorRestClient;
    AbsenceRepo absenceRepo;
    CourseRestClient courseRestClient;


    public  AbsenceRestController(StudentRestClient studentRestClient,ProfessorRestClient professorRestClient,
                                      AbsenceRepo absenceRepo,CourseRestClient courseRestClient){
        this.studentRestClient = studentRestClient;
        this.professorRestClient = professorRestClient;
        this.absenceRepo = absenceRepo;
        this.courseRestClient = courseRestClient;
    }

    @GetMapping("/absences")
    @PreAuthorize("hasAuthority('ETUD')")
    public List<Absence> getAll(){

        System.out.println("listes des absences ");
        return  absenceRepo.findAll();
    }

    @GetMapping("/absences/{id}")
    public  Absence getAbsence(@PathVariable Long id){
        Absence absence = absenceRepo.findById(id).get();
        absence.setCourse(courseRestClient.getCourseById(absence.getIdCourse()));
        absence.setProfessor(professorRestClient.getProfessorById(absence.getIdProf()));
        absence.setStudent(studentRestClient.getStudentById(absence.getIdStudent()));
        return  absence;
    }
    @DeleteMapping("absences/{id}")
    @PreAuthorize("hasAuthority('PROFS')")
    public void deleteAbsence(@PathVariable Long id) {
        absenceRepo.deleteById(id);
    }
}
