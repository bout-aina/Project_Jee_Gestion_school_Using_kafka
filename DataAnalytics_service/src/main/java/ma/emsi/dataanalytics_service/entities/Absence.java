package ma.emsi.dataanalytics_service.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Absence {

    private Long id ;

    private Date dateAbsence;

    private Long idStudent;

    private  Long  idProf;
    private  Long idCourse;
}
