package com.programmingcoursecrud.programmingcoursecrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

    @GetMapping("/")
    public String greeting(String message) {
        message = "Hello, Ico";
        return "greeting";
    }
}
