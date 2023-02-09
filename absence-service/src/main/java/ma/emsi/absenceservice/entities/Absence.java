package ma.emsi.absenceservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.emsi.absenceservice.models.Course;
import ma.emsi.absenceservice.models.Professor;
import ma.emsi.absenceservice.models.Student;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Absence {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @DateTimeFormat(pattern = "dd-jj-yy")
    private Date dateAbsence;

    private Long idStudent;

    private  Long  idProf;
    private  Long idCourse;
    @Transient
    private Professor professor;

    @Transient
    private Student student;

    @Transient
    private Course course;


}
