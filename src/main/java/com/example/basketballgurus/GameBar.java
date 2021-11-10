package com.example.basketballgurus;

import com.example.basketballgurus.models.Game;
import com.example.basketballgurus.repositories.GameRepository;
import com.example.basketballgurus.services.GameBarService;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
        System.out.println(date);
        for(Game game : allGames){
            String pattern = "d";
            SimpleDateFormat sdf = new SimpleDateFormat(pattern);
            Date convDate = new Date(game.getStartTime().getTime() - 3600 * 6000);
            String gameDate =  sdf.format(convDate);
            if (today.equals(gameDate)){
                System.out.println(convDate);
                fiveGames.add(game);
            }
        }
        return fiveGames;
    }

}
