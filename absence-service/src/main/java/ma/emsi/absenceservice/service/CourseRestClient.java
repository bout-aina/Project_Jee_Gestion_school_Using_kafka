package ma.emsi.absenceservice.service;

import ma.emsi.absenceservice.models.Course;
import ma.emsi.absenceservice.models.Professor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "COURSE-SERVICE")
public interface CourseRestClient {
    @GetMapping(path = "/courses/{id}")
    Course getCourseById(@PathVariable Long id);

    @GetMapping(path = "/courses")
    List<Course> getCourses();
}
