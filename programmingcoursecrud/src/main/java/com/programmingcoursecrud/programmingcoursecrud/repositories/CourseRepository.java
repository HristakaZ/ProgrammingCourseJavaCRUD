package com.programmingcoursecrud.programmingcoursecrud.repositories;

import com.programmingcoursecrud.programmingcoursecrud.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
