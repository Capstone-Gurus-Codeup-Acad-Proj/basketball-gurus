package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class EditController {
    @GetMapping("/edit")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "user";
    }
    @PostMapping("/edit")
    public String create(@ModelAttribute User user) {
        return "/profile";
    }
}

