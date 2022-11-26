package com.programmingcoursecrud.programmingcoursecrud.repositories;

import com.programmingcoursecrud.programmingcoursecrud.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
}