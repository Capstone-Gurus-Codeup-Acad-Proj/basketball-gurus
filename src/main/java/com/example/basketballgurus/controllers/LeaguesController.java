package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.League;
import com.example.basketballgurus.models.User;
import com.example.basketballgurus.repositories.LeaguesRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;


@Controller
public class LeaguesController {

    private final LeaguesRepository leaguesDao;

    public LeaguesController(LeaguesRepository leaguesDao) {
        this.leaguesDao = leaguesDao;
    }

    @GetMapping("/leagues")
    public String leaguePageVisitor(Model model) {
        model.addAttribute("league", new League());
        return "leagueVisitor";
    }

    @PostMapping("/leagues/create")
    public String create(@ModelAttribute League league){

        league.setLeagueDifficulty("vdsvsd");
        leaguesDao.save(league);
        return "redirect:/leagues";
    }

}


