package ma.emsi.absenceservice.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Data
public class Course {

    private Long id;


    private String name;


    private String description;

}