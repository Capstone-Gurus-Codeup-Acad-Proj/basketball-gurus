package com.example.basketballgurus.controllers;

import com.example.basketballgurus.services.GameBarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {

    private final GameBarService gm;

    public AuthenticationController(GameBarService gm) {
        this.gm = gm;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("games", gm.getTodaysGames());
        return "user/login";
    }
}

