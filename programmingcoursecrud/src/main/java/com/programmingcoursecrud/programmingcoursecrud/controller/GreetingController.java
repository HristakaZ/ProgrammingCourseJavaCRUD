package com.programmingcoursecrud.programmingcoursecrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/greeting")
public class GreetingController {
    @GetMapping("/get")
    public String greeting(Map<String, Object> model) {
        model.put("message", "Hello Bryan");
        return "greeting";
    }
}
