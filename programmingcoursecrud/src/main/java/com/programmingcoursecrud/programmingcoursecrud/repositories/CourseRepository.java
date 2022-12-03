package com.programmingcoursecrud.programmingcoursecrud.repositories;

import com.programmingcoursecrud.programmingcoursecrud.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findCoursesByName(String name);

    List<Course> findCoursesByDescription(String description);
}
