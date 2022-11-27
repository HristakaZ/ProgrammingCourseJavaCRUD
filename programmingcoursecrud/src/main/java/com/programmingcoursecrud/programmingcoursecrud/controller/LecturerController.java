package com.programmingcoursecrud.programmingcoursecrud.controller;

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

    @GetMapping("/loadUpdateForm")
    public String loadUpdateForm(@RequestParam int id, Map<String, Lecturer> model, ModelMap modelMap) {
        modelMap.addAttribute("lecturer", new Lecturer());
        model.put("lecturerToUpdate", lecturerRepository.findById(id).get());
        return "updateLecturer";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("lecturer") Lecturer lecturer,
                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "updateLecturer";
        }
        Optional<Lecturer> lecturerToUpdate = lecturerRepository.findById(lecturer.getId());
        lecturerToUpdate.get().setName(lecturer.getName());
        lecturerToUpdate.get().setAge(lecturer.getAge());
        lecturerToUpdate.get().setDescription(lecturer.getDescription());

        lecturerRepository.saveAndFlush(lecturerToUpdate.get());

        return "redirect:/lecturer/getAll";
    }

    @GetMapping("/loadDeleteForm")
    public String loadDeleteForm(@RequestParam int id, Map<String, Lecturer> model, ModelMap modelMap) {
        modelMap.addAttribute("lecturer", new Lecturer());
        model.put("lecturerToDelete", lecturerRepository.findById(id).get());
        return "deleteLecturer";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("lecturer") Lecturer lecturer) {
        lecturerRepository.deleteById(lecturer.getId());

        return "redirect:/lecturer/getAll";
    }
}
