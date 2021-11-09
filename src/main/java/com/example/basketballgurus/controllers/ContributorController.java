package com.example.basketballgurus.controllers;

import com.example.basketballgurus.repositories.GameRepository;
import com.example.basketballgurus.services.GameBarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContributorController {

    private final GameRepository gameDao;
    private final GameBarService gm;

    public ContributorController(GameRepository gameDao, GameBarService gm) {
        this.gameDao = gameDao;
        this.gm = gm;
    }

    @GetMapping("/contributor")
    public String landingPage(Model model) {
        model.addAttribute("games", gm.getTodaysGames());
        return "contributor";
    }
}