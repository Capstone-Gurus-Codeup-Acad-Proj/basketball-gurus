package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

// Profile controller bare bones
//will handle edit create and view
public class ProfileController {
    @GetMapping("/Profile")
    public String createProfile(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute User user) {
        return "/profile";
    }
}

