package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class EditController {
    @GetMapping("/edit")
    public void editProfile() {

    }
    @PostMapping("/edit")
    public String create(@ModelAttribute User user) {
        return "/profile";
    }
}

