package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.League;
import com.example.basketballgurus.models.Player;
import com.example.basketballgurus.repositories.LeaguesRepository;
import com.example.basketballgurus.services.GameBarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class LeaguesController {

    private final LeaguesRepository leaguesDao;
    private final GameBarService gm;

    public LeaguesController(LeaguesRepository leaguesDao, GameBarService gm) {
        this.leaguesDao = leaguesDao;
        this.gm = gm;
    }

    @GetMapping("/leagues")
    public String leaguePageVisitor(Model model) {
        model.addAttribute("games", gm.getTodaysGames());
        model.addAttribute("League", new League());
        List<League> allLeagues = leaguesDao.findAll();
        model.addAttribute("leagues", allLeagues);
        return "MyLeague";


    }

    @PostMapping("/leagues/create")
    public String create(@ModelAttribute League league, Model model){

        model.addAttribute("games", gm.getTodaysGames());
        league.setLeagueDifficulty("100");
        leaguesDao.save(league);
        return "redirect:/leagues";
    }




}


