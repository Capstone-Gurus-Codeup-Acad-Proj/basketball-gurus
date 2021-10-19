package com.example.basketballgurus.services;

import com.example.basketballgurus.models.Player;
import com.example.basketballgurus.repos.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
@Service
public class PlayerMethod implements PlayerControllerService {

    private final PlayerRepository playerDao;

    public PlayerMethod(PlayerRepository playerDao) {
        this.playerDao = playerDao;
    }

    @Override
    public String showPlayers(Model model) {
        List<Player> allPlayers = playerDao.findAll();
        model.addAttribute("players", allPlayers);
        return "/playerList";
    }
}
