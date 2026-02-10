package com.moussa.rimma_backend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @GetMapping("/")
    public String index() {
        return "Bienvenue dans l'application RIMMA Backend.";
    }
}
