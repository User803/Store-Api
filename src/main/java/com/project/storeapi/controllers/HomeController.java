package com.project.storeapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

    public String index() {
        return "index";
    }

    @GetMapping("/hello")
    public String sayHello(Model model) {
        model.addAttribute("name", "Spring MVC");
        return "home";
    }
}
