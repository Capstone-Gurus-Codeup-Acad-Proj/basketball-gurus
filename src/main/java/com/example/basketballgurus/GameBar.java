package com.example.basketballgurus;

import com.example.basketballgurus.models.Game;
import com.example.basketballgurus.repositories.GameRepository;
import com.example.basketballgurus.services.GameBarService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class GameBar implements GameBarService {

    private GameRepository gameDao;

    public GameBar(GameRepository gameDao) {
        this.gameDao = gameDao;
    }


    public ArrayList<Game> getTodaysGames() {
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
        return fiveGames;
    }

}
