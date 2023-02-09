package ma.emsi.professorsservice.repositories;

import ma.emsi.professorsservice.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
