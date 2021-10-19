package com.example.basketballgurus.controllers;

import com.example.basketballgurus.models.Player;
import com.example.basketballgurus.repos.PlayerRepository;
import com.example.basketballgurus.services.PlayerControllerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PlayerController {

    private final PlayerControllerService playerDao;

    public PlayerController(PlayerControllerService playerDao) {
        this.playerDao = playerDao;
    }


    @GetMapping("/players")
    public String showPlayers(Model model) {
      return playerDao.showPlayers(model);
    }

//    @GetMapping("/players{id}")
//    public String showOnePlayer(@PathVariable long id, Model model) {
//        Player player = playerDao.getById(id);
//        model.addAttribute("playerId", id);
//        model.addAttribute("player", player);
//        return "/playerList";
//    }

}
