package com.example.basketballgurus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
public class LandingPageController {
    @GetMapping("/homes")
    public String landingPage() {
        return "landingPage";
    }
}
