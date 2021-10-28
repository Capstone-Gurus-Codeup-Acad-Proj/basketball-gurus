package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.League;
import com.example.basketballgurus.repositories.LeaguesRepository;
import com.example.basketballgurus.services.GameBarService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Controller
public class LeaguesController {

    private final LeaguesRepository leaguesDao;
    private final GameBarService gm;
    private final PasswordEncoder passwordEncoder;

    public LeaguesController(LeaguesRepository leaguesDao, GameBarService gm, PasswordEncoder passwordEncoder) {
        this.leaguesDao = leaguesDao;
        this.gm = gm;
        this.passwordEncoder = passwordEncoder;
    }
    @GetMapping("/leagues")
    public String leaguePageVisitor(Model model) {
        model.addAttribute("games", gm.getTodaysGames());
        model.addAttribute("League", new League());
        List<League> allLeagues = leaguesDao.findAll();
        model.addAttribute("leagues", allLeagues);
        return "allLeagues";

    }

    @GetMapping("/leagues/{uuid}")
    public String myLeague(@PathVariable String uuid, Model model) {
        model.addAttribute("league", leaguesDao.getByUuid(uuid));
        return "league";
    }
    @GetMapping("/leagues/join/{id}")
    public String leagueJoin(@PathVariable int id, Model model) {
        model.addAttribute("leagueId", id);
        return "leagueJoin";
    }

    @PostMapping("/leagues/create")
    public String create(@ModelAttribute League league, Model model){

        model.addAttribute("games", gm.getTodaysGames());
        league.setLeagueDifficulty("100");
        String hash = passwordEncoder.encode(league.getPassword());
        league.setPassword(hash);
        leaguesDao.save(league);
        return "redirect:/leagues";
    }




}


