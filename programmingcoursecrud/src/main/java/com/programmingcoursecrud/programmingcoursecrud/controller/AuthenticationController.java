package com.programmingcoursecrud.programmingcoursecrud.controller;

import com.programmingcoursecrud.programmingcoursecrud.model.User;
import com.programmingcoursecrud.programmingcoursecrud.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.TypedQuery;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/authentication")
public class AuthenticationController {

    private final UserRepository userRepository;
    private HttpSession httpSession;

    public AuthenticationController(UserRepository userRepository,
                                    HttpSession httpSession) {
        this.userRepository = userRepository;
        this.httpSession = httpSession;
    }

    @GetMapping("/loadRegisterForm")
    public String loadRegisterForm(ModelMap modelMap,
                                   Map<String, String> userEmail) {
        modelMap.addAttribute("user", new User());
        if(httpSession.getAttribute("userEmail") != null)
        {
            userEmail.put("userEmail", httpSession.getAttribute("userEmail").toString());
        }
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "register";
        }

        //TO DO: hash password
        userRepository.saveAndFlush(user);
        return "redirect:/course/getAll";
    }

    @GetMapping("/loadLoginForm")
    public String loadLoginForm(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user,
                        BindingResult bindingResult,
                        Map<String, String> userEmail) {
        //TO DO: hash password
        User userFromDb = userRepository.findByEmailAndPassword(user.getEmail(), user.getPassword());
        if(userFromDb != null) {
            httpSession.setAttribute("userEmail", userFromDb.getEmail());
            return "redirect:/course/getAll";
        }

        bindingResult.rejectValue("email", "error", "A user with this email and password does not exist.");
        return "login";
    }

    @PostMapping("/logout")
    public String logout() {
        httpSession.setAttribute("userEmail", null);
        return "redirect:/course/getAll";
    }
}
