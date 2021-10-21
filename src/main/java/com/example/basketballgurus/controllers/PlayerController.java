package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.Player;
import com.example.basketballgurus.repositories.PlayerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PlayerController {

    private final PlayerRepository playerDao;

    public PlayerController(PlayerRepository playerDao) {
        this.playerDao = playerDao;
    }

    @GetMapping("/players")
    public String showPlayers(Model model) {
        List<Player> allPlayers = playerDao.findAll();
        model.addAttribute("players", allPlayers);
        return "playerList";
    }

    @RequestMapping(value = "/players", method = RequestMethod.POST)
    public String getPlayerByName(@RequestParam(name = "search") String search, Model model) {
        List<Player> player = playerDao.findByFirstNameOrLastName(search);
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
