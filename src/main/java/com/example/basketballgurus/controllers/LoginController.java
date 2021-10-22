package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.User;
import com.example.basketballgurus.repositories.UserRepository;
import com.example.basketballgurus.services.GameBarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class LoginController {

   private final UserRepository userDao;
   private final GameBarService gm;

    public LoginController(UserRepository userDao, GameBarService gm) {
        this.userDao = userDao;
        this.gm = gm;
    }
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("games", gm.getTodaysGames());
        return "user/login";
    }

    @PostMapping("/login")
    public String show(@ModelAttribute User user, Model model) {
        model.addAttribute("games", gm.getTodaysGames());
        return "redirect:/";
    }
}