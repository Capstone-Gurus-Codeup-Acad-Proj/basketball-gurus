package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.Game;
import com.example.basketballgurus.models.Player;
import com.example.basketballgurus.repositories.GameRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class LandingPageController {

    private final GameRepository gameDao;

    public LandingPageController(GameRepository gameDao) {
        this.gameDao = gameDao;
    }

    @GetMapping("/home")
    public String landingPage(Model model) {
        List<Game> allGames = gameDao.findAll();
        ArrayList<Game> fiveGames = new ArrayList<>();
        for(Game game : allGames){
            if (fiveGames.size() == 10){
                break;
            }
            if (!game.getFinished()){

                fiveGames.add(game);
            }
        }
        model.addAttribute("games", fiveGames);
        return "landingPage";
    }
}
