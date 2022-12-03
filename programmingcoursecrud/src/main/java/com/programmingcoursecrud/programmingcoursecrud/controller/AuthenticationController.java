package com.programmingcoursecrud.programmingcoursecrud.controller;

import com.programmingcoursecrud.programmingcoursecrud.model.User;
import com.programmingcoursecrud.programmingcoursecrud.repositories.UserRepository;
import com.programmingcoursecrud.programmingcoursecrud.services.AuthenticationService;
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
    private AuthenticationService authenticationService;

    public AuthenticationController(UserRepository userRepository,
                                    AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/loadRegisterForm")
    public String loadRegisterForm(ModelMap modelMap,
                                   Map<String, String> userEmail) {
        modelMap.addAttribute("user", new User());
        if(authenticationService.isAuthenticated())
        {
            userEmail.put("userEmail", authenticationService.getAuthenticatedUserEmail());
        }

        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user,
                           BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "register";
        }

        String hashedPassword = authenticationService.hashPassword(user.getPassword());
        user.setPassword(hashedPassword);
        userRepository.saveAndFlush(user);
        return "redirect:/course/getAll";
    }

    @GetMapping("/loadLoginForm")
    public String loadLoginForm(ModelMap modelMap, Map<String, String> userEmail) {
        modelMap.addAttribute("user", new User());
        if(authenticationService.isAuthenticated())
        {
            userEmail.put("userEmail", authenticationService.getAuthenticatedUserEmail());
        }

        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user,
                        BindingResult bindingResult) {
        User userFromDb = new User();
        if (authenticationService.isPasswordMatchingHashedPassword(user.getPassword())) {
            userFromDb = userRepository.findByEmail(user.getEmail());
        }

        if(userFromDb != null) {
            authenticationService.login(userFromDb.getEmail());
            return "redirect:/course/getAll";
        }

        bindingResult.rejectValue("email", "error", "A user with this email and password does not exist.");
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        authenticationService.logout();
        return "redirect:/course/getAll";
    }
}
