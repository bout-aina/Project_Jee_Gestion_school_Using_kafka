package ma.emsi.absenceservice.repos;

import ma.emsi.absenceservice.entities.Absence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AbsenceRepo extends JpaRepository<Absence,Long> {

}
