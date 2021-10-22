package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.Game;
import com.example.basketballgurus.models.Player;
import com.example.basketballgurus.repositories.GameRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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
        LocalDate date = LocalDate.now();
        String today = Integer.toString(date.getDayOfMonth());
        for(Game game : allGames){
            String pattern = "d";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            String gameDate =  sdf.format(game.getStartTime());
            if (today.equals(gameDate)){
                fiveGames.add(game);
            }
        }
        model.addAttribute("games", fiveGames);
        return "landingPage";
    }
}
