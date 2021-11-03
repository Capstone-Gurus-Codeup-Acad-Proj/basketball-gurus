package com.example.basketballgurus.controllers;

import com.example.basketballgurus.LeagueScoreCalculator;
import com.example.basketballgurus.models.League;
import com.example.basketballgurus.repositories.LeaguesRepository;
import com.example.basketballgurus.repositories.PlayerRepository;
import com.example.basketballgurus.repositories.TeamRepository;
import com.example.basketballgurus.services.GameBarService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class LeaguesController {

    private final LeaguesRepository leaguesDao;
    private final GameBarService gm;
    private final PasswordEncoder passwordEncoder;
    private final PlayerRepository playerDao;
    private final TeamRepository teamDao;
    private final LeagueScoreCalculator lsc;

    public LeaguesController(LeaguesRepository leaguesDao, GameBarService gm, PasswordEncoder passwordEncoder, PlayerRepository playerDao, TeamRepository teamDao, LeagueScoreCalculator lsc) {
        this.leaguesDao = leaguesDao;
        this.gm = gm;
        this.passwordEncoder = passwordEncoder;
        this.playerDao = playerDao;
        this.teamDao = teamDao;
        this.lsc = lsc;
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
        League league = leaguesDao.getByUuid(uuid);
        model.addAttribute("league", league);
        model.addAttribute("leagueScores", lsc.getLeagueScores(league));
        return "league";
    }
    @GetMapping("/leagues/join/{id}")
    public String leagueJoin(@PathVariable int id, Model model) {
        model.addAttribute("leagueId", id);
        return "leagueJoin";
    }

    @PostMapping("/leagues/join")
    public String leagueJoin(@RequestParam int leagueId, @RequestParam String password, Model model){

        League league = leaguesDao.getById(leagueId);

        if(BCrypt.checkpw(password, league.getPassword())){

            model.addAttribute("leagueId", leagueId);
            model.addAttribute("league", leaguesDao.getById(leagueId));
            model.addAttribute("failedLogin", false);
            return "leagueJoin";

        }else{
            model.addAttribute("leagueId", leagueId);
            model.addAttribute("failedLogin", true);
            return "leagueJoin";
        }

    }

    @PostMapping("/leagues/create")
    public String create(@ModelAttribute League league, Model model){

        model.addAttribute("games", gm.getTodaysGames());
        switch (league.getLeagueDifficulty()) {

            case "Easy": league.setLeagueDifficulty("3500");
            break;
            case "Medium": league.setLeagueDifficulty("2500");
            break;
            case "Hard": league.setLeagueDifficulty("2000");
            break;

        }
        String hash = BCrypt.hashpw(league.getPassword(), BCrypt.gensalt());
        league.setPassword(hash);
        leaguesDao.save(league);
        return "redirect:/leagues";
    }




}


