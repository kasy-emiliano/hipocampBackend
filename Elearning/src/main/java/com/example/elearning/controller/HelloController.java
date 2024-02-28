package com.example.elearning.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin
@RestController

public class HelloController {
@GetMapping ("/hello")
public String bienvenue(Model model){

    return "test";
}

}
