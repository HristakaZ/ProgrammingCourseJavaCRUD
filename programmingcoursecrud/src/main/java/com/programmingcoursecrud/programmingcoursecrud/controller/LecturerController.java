package com.programmingcoursecrud.programmingcoursecrud.controller;

import com.programmingcoursecrud.programmingcoursecrud.model.Lecturer;
import com.programmingcoursecrud.programmingcoursecrud.repositories.LecturerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

    private final LecturerRepository lecturerRepository;

    public LecturerController(LecturerRepository lecturerRepository) {
        this.lecturerRepository = lecturerRepository;
    }

    @GetMapping("/getAll")
    public String getAll(Map<String, List<Lecturer>> model) {
        List<Lecturer> lecturersAndTheirCourses = lecturerRepository.findAll();
        model.put("lecturers", lecturersAndTheirCourses);
        return "getAllLecturers";
    }

    @GetMapping("/getById/{id}")
    public String getById(@PathVariable int id, @ModelAttribute("lecturer") Optional<Lecturer> lecturer) {
        lecturer = lecturerRepository.findById(id);
        return "getLecturerById";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute("lecturer") Lecturer lecturer) {
        lecturerRepository.saveAndFlush(lecturer);
        return "createLecturer";
    }

    @PatchMapping("update")
    public String update(@PathVariable Lecturer lecturer) {
        Optional<Lecturer> lecturerToUpdate = lecturerRepository.findById(lecturer.getId());
        lecturerToUpdate.get().setName(lecturer.getName());
        lecturerToUpdate.get().setAge(lecturer.getAge());
        lecturerToUpdate.get().setDescription(lecturer.getDescription());
        lecturerToUpdate.get().setCourses(lecturer.getCourses());

        lecturerRepository.saveAndFlush(lecturerToUpdate.get());

        return "updateLecturer";
    }

    @DeleteMapping("delete")
    public String delete(@PathVariable int id) {
        lecturerRepository.deleteById(id);

        return "deleteLecturer";
    }
}
