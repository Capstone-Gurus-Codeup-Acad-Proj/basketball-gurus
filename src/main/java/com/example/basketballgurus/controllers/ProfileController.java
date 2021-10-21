package com.example.basketballgurus.controllers;

import com.example.basketballgurus.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// Profile controller bare bones
//will handle edit create and view
@Controller()
public class ProfileController {
    private final UserRepository userDao;

    public ProfileController(UserRepository userDao) {
        this.userDao = userDao;
    }

    @GetMapping("profile/login")
    public String login(Model model, String error, String logout) {
    if (error != null)
        model.addAttribute("error", "Your Username and Password is invalid.");
    if (logout != null)
        model.addAttribute("message", "You Have Been Logged Out Successfully.");
    return "redirect:/landingPage";
    }
    @GetMapping({"/profile"})
    public String profile(Model model) {
        return "/Profile";
    }
}