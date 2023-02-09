package ma.emsi.courseservice.repositorys;

import ma.emsi.courseservice.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
