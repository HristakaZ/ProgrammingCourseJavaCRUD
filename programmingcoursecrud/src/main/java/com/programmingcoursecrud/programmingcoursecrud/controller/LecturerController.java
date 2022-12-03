package com.programmingcoursecrud.programmingcoursecrud.controller;

import com.mysql.cj.util.StringUtils;
import com.programmingcoursecrud.programmingcoursecrud.model.Course;
import com.programmingcoursecrud.programmingcoursecrud.model.Lecturer;
import com.programmingcoursecrud.programmingcoursecrud.model.SearchCriteria;
import com.programmingcoursecrud.programmingcoursecrud.repositories.CourseRepository;
import com.programmingcoursecrud.programmingcoursecrud.repositories.LecturerRepository;
import com.programmingcoursecrud.programmingcoursecrud.services.AuthenticationService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.function.Supplier;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

    private final LecturerRepository lecturerRepository;
    private final AuthenticationService authenticationService;

    public LecturerController(LecturerRepository lecturerRepository,
                              AuthenticationService authenticationService) {
        this.lecturerRepository = lecturerRepository;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/getAll")
    public String getAll(Map<String, List<Lecturer>> model,
                         Map<String, String> userEmail,
                         ModelMap modelMap) {
        modelMap.addAttribute("searchCriteria", new SearchCriteria());
        if(authenticationService.isAuthenticated())
        {
            userEmail.put("userEmail", authenticationService.getAuthenticatedUserEmail());
        }
        List<Lecturer> lecturers = lecturerRepository.findAll();
        model.put("lecturers", lecturers);
        return "getAllLecturers";
    }

    @PostMapping("/search")
    public String search(Map<String, List<Lecturer>> model,
                         Map<String, String> userEmail,
                         @ModelAttribute("searchCriteria") SearchCriteria searchCriteria) {
        Map<String, Supplier<List<Lecturer>>> criteriaToSearchBy = new HashMap<>();
        criteriaToSearchBy.put("name", new Supplier<List<Lecturer>>() {
            @Override
            public List<Lecturer> get() {
                return lecturerRepository.findLecturersByName(searchCriteria.getSearch());
            }
        });

        criteriaToSearchBy.put("age", new Supplier<List<Lecturer>>() {
            @Override
            public List<Lecturer> get() {
                return lecturerRepository.findLecturersByAge(Integer.parseInt(searchCriteria.getSearch()));
            }
        });

        criteriaToSearchBy.put("description", new Supplier<List<Lecturer>>() {
            @Override
            public List<Lecturer> get() {
                return lecturerRepository.findLecturersByDescription(searchCriteria.getSearch());
            }
        });

        if(authenticationService.isAuthenticated()) {
            userEmail.put("userEmail", authenticationService.getAuthenticatedUserEmail());
        }

        List<Lecturer> lecturers = new ArrayList<>();
        if(!StringUtils.isEmptyOrWhitespaceOnly(searchCriteria.getSearch()) &&
                !StringUtils.isEmptyOrWhitespaceOnly(searchCriteria.getCriteria())) {
            if (criteriaToSearchBy.containsKey(searchCriteria.getCriteria())) {
                lecturers = criteriaToSearchBy.get(searchCriteria.getCriteria()).get();
            }
        }
        else {
            lecturers = lecturerRepository.findAll();
        }
        model.put("lecturers", lecturers);

        return "getAllLecturers";
    }

    @GetMapping("/getById")
    public String getById(@RequestParam int id,
                          Map<String, Lecturer> model,
                          Map<String, String> userEmail) {
        if(authenticationService.isAuthenticated())
        {
            userEmail.put("userEmail", authenticationService.getAuthenticatedUserEmail());
        }
        model.put("lecturer", lecturerRepository.findById(id).get());
        return "getLecturerById";
    }

    @GetMapping("/loadCreateForm")
    public String loadCreateForm(ModelMap modelMap,
                                 Map<String, String> userEmail) {
        if(authenticationService.isAuthenticated())
        {
            userEmail.put("userEmail", authenticationService.getAuthenticatedUserEmail());
        }
        modelMap.addAttribute("lecturer", new Lecturer());
        return "createLecturer";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("lecturer") Lecturer lecturer,
                         BindingResult bindingResult,
                         Map<String, String> userEmail) {
        if(authenticationService.isAuthenticated())
        {
            userEmail.put("userEmail", authenticationService.getAuthenticatedUserEmail());
        }

        if(bindingResult.hasErrors()) {
            return "createLecturer";
        }

        lecturerRepository.saveAndFlush(lecturer);
        return "redirect:/lecturer/getAll";
    }

    @GetMapping("/loadUpdateForm")
    public String loadUpdateForm(@RequestParam int id,
                                 Map<String, Lecturer> model,
                                 ModelMap modelMap,
                                 Map<String, String> userEmail) {
        if(authenticationService.isAuthenticated())
        {
            userEmail.put("userEmail", authenticationService.getAuthenticatedUserEmail());
        }
        modelMap.addAttribute("lecturer", new Lecturer());
        model.put("lecturerToUpdate", lecturerRepository.findById(id).get());
        return "updateLecturer";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("lecturer") Lecturer lecturer,
                         BindingResult bindingResult,
                         Map<String, String> userEmail) {
        if(authenticationService.isAuthenticated())
        {
            userEmail.put("userEmail", authenticationService.getAuthenticatedUserEmail());
        }

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
    public String loadDeleteForm(@RequestParam int id,
                                 Map<String, Lecturer> model,
                                 ModelMap modelMap,
                                 Map<String, String> userEmail) {
        if(authenticationService.isAuthenticated())
        {
            userEmail.put("userEmail", authenticationService.getAuthenticatedUserEmail());
        }
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
