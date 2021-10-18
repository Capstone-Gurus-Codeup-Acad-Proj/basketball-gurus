package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class ViewController {
    @GetMapping("/view")
    public Model viewProfile(Model model) {
      return model;
    }
    @PostMapping("/view")
    public String create(@ModelAttribute User user) {
        return "/profile";
    }
}

