package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.Player;
import com.example.basketballgurus.models.Team;
import com.example.basketballgurus.repositories.PlayerRepository;
import com.example.basketballgurus.repositories.TeamRepository;
import com.example.basketballgurus.services.GameBarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlayerController {

    private final PlayerRepository playerDao;
    private final GameBarService gm;
    private final TeamRepository teamDao;

    public PlayerController(PlayerRepository playerDao, GameBarService gm, TeamRepository teamDao) {
        this.playerDao = playerDao;
        this.gm = gm;
        this.teamDao = teamDao;
    }

    @GetMapping("/players")
    public String showPlayers(Model model) {
        List<Player> allPlayers = playerDao.findAll();
        List<Team> allTeams = teamDao.findAll();
        model.addAttribute("games", gm.getTodaysGames());
        model.addAttribute("players", allPlayers);
        model.addAttribute("teams", allTeams);
        return "playerList";
    }

    @RequestMapping(value = "/players", method = RequestMethod.POST)
    public String getPlayerByName(@RequestParam(name = "search") String search, Model model) {
        List<Player> player = playerDao.findByFirstNameOrLastName(search);
        model.addAttribute("players", player);
        return "playerList";
    }


    @RequestMapping(value = "/players/team", method = RequestMethod.POST)
    public String getTeamByName(@RequestParam(name = "team") String team, Model model) {
        Team teamFilter = teamDao.findTeamByFullName(team);
        List<Player> player = playerDao.findByTeamId(teamFilter);
        model.addAttribute("players", player);
        return "playerList";
    }

//    @GetMapping("/players{id}")
//    public String showOnePlayer(@PathVariable long id, Model model) {
//        Player player = playerDao.getById(id);
//        model.addAttribute("playerId", id);
//        model.addAttribute("player", player);
//        return "/playerList";
//    }

}
