package com.example.basketballgurus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class ProfileController {
    @GetMapping("/profile")
    public String landingPage() {
        return "profile";
    }
}
