package com.example.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    // localhost8080일 때 호출
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
