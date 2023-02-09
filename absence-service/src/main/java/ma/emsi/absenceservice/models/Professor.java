package ma.emsi.absenceservice.models;

import lombok.Data;

import javax.persistence.Column;

@Data
public class Professor {
    private Long id;

    private String name;

    private String department;
}
