package com.programmingcoursecrud.programmingcoursecrud.controller;

import com.programmingcoursecrud.programmingcoursecrud.model.Course;
import com.programmingcoursecrud.programmingcoursecrud.model.Lecturer;
import com.programmingcoursecrud.programmingcoursecrud.repositories.CourseRepository;
import com.programmingcoursecrud.programmingcoursecrud.repositories.LecturerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

    private final LecturerRepository lecturerRepository;
    private final CourseRepository courseRepository;

    public LecturerController(LecturerRepository lecturerRepository,
                              CourseRepository courseRepository) {
        this.lecturerRepository = lecturerRepository;
        this.courseRepository = courseRepository;
    }

    @GetMapping("/getAll")
    public String getAll(Map<String, List<Lecturer>> model) {
        List<Lecturer> lecturers = lecturerRepository.findAll();
        model.put("lecturers", lecturers);
        return "getAllLecturers";
    }

    @GetMapping("/getById")
    public String getById(@RequestParam int id, Map<String, Lecturer> model) {
        model.put("lecturer", lecturerRepository.findById(id).get());
        return "getLecturerById";
    }

    @GetMapping("/loadCreateForm")
    public String loadCreateForm(ModelMap modelMap) {
        modelMap.addAttribute("lecturer", new Lecturer());
        return "createLecturer";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("lecturer") Lecturer lecturer,
                         BindingResult bindingResult) {
        lecturer.setCourses(new ArrayList<>());
        if(bindingResult.hasErrors()) {
            return "createLecturer";
        }

        lecturerRepository.saveAndFlush(lecturer);
        return "redirect:/lecturer/getAll";
    }

    @PatchMapping("/update")
    public String update(@PathVariable Lecturer lecturer) {
        Optional<Lecturer> lecturerToUpdate = lecturerRepository.findById(lecturer.getId());
        lecturerToUpdate.get().setName(lecturer.getName());
        lecturerToUpdate.get().setAge(lecturer.getAge());
        lecturerToUpdate.get().setDescription(lecturer.getDescription());
        lecturerToUpdate.get().setCourses(lecturer.getCourses());

        lecturerRepository.saveAndFlush(lecturerToUpdate.get());

        return "getAllLecturers";
    }

    @DeleteMapping("/delete")
    public String delete(@PathVariable int id) {
        lecturerRepository.deleteById(id);

        return "getAllLecturers";
    }
}
