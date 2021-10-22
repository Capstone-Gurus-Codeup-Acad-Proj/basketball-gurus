package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.User;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.basketballgurus.repositories.UserRepository;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    private final UserRepository userDao;
    private final PasswordEncoder passwordEncoder;

    public RegisterController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "user/register";
    }

    @PostMapping("/register")
    public String create(@ModelAttribute User user) {
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
//        user.setActive(true);
        userDao.save(user);
        return "user/login";
    }
}