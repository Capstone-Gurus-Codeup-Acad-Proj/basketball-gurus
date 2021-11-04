package com.example.basketballgurus;

import com.example.basketballgurus.RestModels.GameStatsModel;
import com.example.basketballgurus.models.Player;
import com.example.basketballgurus.repositories.PlayerRepository;
import com.example.basketballgurus.services.PlayerPriceService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PlayerPrice implements PlayerPriceService {

    private final PlayerRepository playerDao;

    public PlayerPrice(PlayerRepository playerDao) {
        this.playerDao = playerDao;
    }

    public void createPlayerPrice(){

        List<Player> players = playerDao.findAll();

        for (Player player : players){

            player.setPrice(generatePrice(player));
            playerDao.save(player);

        }

    }

    public int generatePrice(Player player){
        int total = 0;

        total += player.getPoint();
        total += player.getAssists() * 1.5;
        total += player.getRebounds() * 1.5;
        total += player.getBlocks() * 3;
        total -= player.getTurnOvers();

        int finalTotal = (total + 2) * 10;


        return ((finalTotal + 49) / 50) * 50;

    }

}
