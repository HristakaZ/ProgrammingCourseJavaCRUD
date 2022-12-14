package com.programmingcoursecrud.programmingcoursecrud.controller;

import com.mysql.cj.util.StringUtils;
import com.programmingcoursecrud.programmingcoursecrud.model.Course;
import com.programmingcoursecrud.programmingcoursecrud.model.Lecturer;
import com.programmingcoursecrud.programmingcoursecrud.model.SearchCriteria;
import com.programmingcoursecrud.programmingcoursecrud.repositories.CourseRepository;
import com.programmingcoursecrud.programmingcoursecrud.repositories.LecturerRepository;
import com.programmingcoursecrud.programmingcoursecrud.services.AuthenticationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

@Controller
@RequestMapping("/course")
public class CourseController {
    private final CourseRepository courseRepository;
    private final LecturerRepository lecturerRepository;
    private final AuthenticationService authenticationService;

    public CourseController(CourseRepository courseRepository,
                            LecturerRepository lecturerRepository,
                            AuthenticationService authenticationService) {
        this.courseRepository = courseRepository;
        this.lecturerRepository = lecturerRepository;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/getAll")
    public String getAll(Map<String, List<Course>> model,
                         Map<String, String> userEmail,
                         ModelMap modelMap) {
        if(authenticationService.isAuthenticated())
        {
            userEmail.put("userEmail", authenticationService.getAuthenticatedUserEmail());
            modelMap.addAttribute("searchCriteria", new SearchCriteria());
            List<Course> courses = courseRepository.findAll();
            model.put("courses", courses);
            return "getAllCourses";
        }

        return "redirect:/authentication/loadLoginForm";
    }

    @PostMapping("/search")
    public String search(Map<String, List<Course>> model,
                         Map<String, String> userEmail,
                         @ModelAttribute("searchCriteria") SearchCriteria searchCriteria) {
        if(authenticationService.isAuthenticated()) {
            userEmail.put("userEmail", authenticationService.getAuthenticatedUserEmail());
            Map<String, Supplier<List<Course>>> criteriaToSearchBy = new HashMap<>();
            criteriaToSearchBy.put("name", new Supplier<List<Course>>() {
                @Override
                public List<Course> get() {
                    return courseRepository.findCoursesByName(searchCriteria.getSearch());
                }
            });
            criteriaToSearchBy.put("description", new Supplier<List<Course>>() {
                @Override
                public List<Course> get() {
                    return courseRepository.findCoursesByDescription(searchCriteria.getSearch());
                }
            });
            List<Course> courses = new ArrayList<>();
            if(!StringUtils.isEmptyOrWhitespaceOnly(searchCriteria.getSearch()) &&
                    !StringUtils.isEmptyOrWhitespaceOnly(searchCriteria.getCriteria())) {
                if(criteriaToSearchBy.containsKey(searchCriteria.getCriteria())) {
                    courses = criteriaToSearchBy.get(searchCriteria.getCriteria()).get();
                }
            }
            else {
                courses = courseRepository.findAll();
            }
            model.put("courses", courses);

            return "getAllCourses";
        }

        return "redirect:/authentication/loadLoginForm";
    }

    @GetMapping("/getById")
    public String getById(@RequestParam int id,
                          Map<String, Course> model,
                          Map<String, String> userEmail) {
        if(authenticationService.isAuthenticated())
        {
            userEmail.put("userEmail", authenticationService.getAuthenticatedUserEmail());
            model.put("course", courseRepository.findById(id).get());
            return "getCourseById";
        }

        return "redirect:/authentication/loadLoginForm";
    }

    @GetMapping("/loadCreateForm")
    public String loadCreateForm(ModelMap modelMap,
                                 Map<String, List<Lecturer>> lecturers,
                                 Map<String, String> userEmail) {
        if(authenticationService.isAuthenticated())
        {
            userEmail.put("userEmail", authenticationService.getAuthenticatedUserEmail());
            modelMap.addAttribute("course", new Course());
            lecturers.put("lecturers", lecturerRepository.findAll());
            return "createCourse";
        }

        return "redirect:/authentication/loadLoginForm";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("course") Course course,
                         BindingResult bindingResult,
                         Map<String, List<Lecturer>> lecturers,
                         Map<String, String> userEmail) {
        if(authenticationService.isAuthenticated())
        {
            userEmail.put("userEmail", authenticationService.getAuthenticatedUserEmail());
            lecturers.put("lecturers", lecturerRepository.findAll());
            if(bindingResult.hasErrors()) {
                return "createCourse";
            }

            courseRepository.saveAndFlush(course);
            return "redirect:/course/getAll";
        }

        return "redirect:/authentication/loadLoginForm";
    }

    @GetMapping("/loadUpdateForm")
    public String loadUpdateForm(@RequestParam int id,
                                 Map<String, Course> model,
                                 Map<String, List<Lecturer>> lecturers,
                                 ModelMap modelMap,
                                 Map<String, String> userEmail) {
        if(authenticationService.isAuthenticated())
        {
            userEmail.put("userEmail", authenticationService.getAuthenticatedUserEmail());
            modelMap.addAttribute("course", new Course());
            model.put("courseToUpdate", courseRepository.findById(id).get());
            lecturers.put("lecturers", getUnselectedLecturers(id));
            return "updateCourse";
        }

        return "redirect:/authentication/loadLoginForm";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("course") Course course,
                         BindingResult bindingResult,
                         Map<String, List<Lecturer>> lecturers,
                         Map<String, Course> model,
                         Map<String, String> userEmail) {
        if(authenticationService.isAuthenticated())
        {
            userEmail.put("userEmail", authenticationService.getAuthenticatedUserEmail());
            model.put("courseToUpdate", courseRepository.findById(course.getId()).get());
            lecturers.put("lecturers", getUnselectedLecturers(course.getId()));
            if(bindingResult.hasErrors()) {
                return "updateCourse";
            }
            Optional<Course> courseToUpdate = courseRepository.findById(course.getId());
            courseToUpdate.get().setName(course.getName());
            courseToUpdate.get().setDescription(course.getDescription());
            courseToUpdate.get().setLecturer(course.getLecturer());

            courseRepository.saveAndFlush(courseToUpdate.get());

            return "redirect:/course/getAll";
        }

        return "redirect:/authentication/loadLoginForm";
    }

    @GetMapping("/loadDeleteForm")
    public String loadDeleteForm(@RequestParam int id,
                                 Map<String, Course> model,
                                 ModelMap modelMap,
                                 Map<String, String> userEmail) {
        if(authenticationService.isAuthenticated())
        {
            userEmail.put("userEmail", authenticationService.getAuthenticatedUserEmail());
            modelMap.addAttribute("course", new Course());
            model.put("courseToDelete", courseRepository.findById(id).get());
            return "deleteCourse";
        }

        return "redirect:/authentication/loadLoginForm";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute("course") Course course) {
        if(authenticationService.isAuthenticated()) {
            courseRepository.deleteById(course.getId());
            return "redirect:/course/getAll";
        }

        return "redirect:/authentication/loadLoginForm";
    }

    private List<Lecturer> getUnselectedLecturers(int id) {
        List<Lecturer> unselectedLecturers = new ArrayList<>();
        List<Lecturer> allLecturers = lecturerRepository.findAll();
        Lecturer selectedLecturer = courseRepository.findById(id).get().getLecturer();
        for (Lecturer lecturer: allLecturers) {
            if(lecturer.getId() != selectedLecturer.getId()) {
                unselectedLecturers.add(lecturer);
            }
        }

        return unselectedLecturers;
    }
}
