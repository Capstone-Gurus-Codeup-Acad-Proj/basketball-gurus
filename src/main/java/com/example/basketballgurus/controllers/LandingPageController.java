package com.example.basketballgurus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class LandingPageController {
    @GetMapping("/home")
    public String landingPage() {
        return "landingPage";
    }
}
