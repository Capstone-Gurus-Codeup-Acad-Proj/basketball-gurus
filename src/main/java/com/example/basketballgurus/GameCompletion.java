package com.example.basketballgurus;

import com.example.basketballgurus.RestModels.GameModel;
import com.example.basketballgurus.models.Game;
import com.example.basketballgurus.models.PlayerScore;
import com.example.basketballgurus.repositories.GameRepository;
import com.example.basketballgurus.repositories.PlayerScoreRepository;
import com.example.basketballgurus.repositories.TeamRepository;
import com.example.basketballgurus.services.GameCompletionService;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@EnableScheduling
@Transactional
public class GameCompletion implements GameCompletionService {

    private final GameRepository gameDao;
    private final ScoreMaker scoreMaker;
    private final PlayerScoreRepository scoreDao;

    public GameCompletion(@Lazy GameRepository gameDao, ScoreMaker scoreMaker,@Lazy PlayerScoreRepository scoreDao) {
        this.scoreDao = scoreDao;
        this.gameDao = gameDao;
        this.scoreMaker = scoreMaker;
    }

//    @Override
//    @Scheduled(cron = "0 */10 * * * *")
    public void checkStatus(ArrayList<GameModel> weeksGames) throws IOException {


        for(GameModel game : weeksGames){
            Game dbGame = gameDao.getById(game.gameId);
            List<PlayerScore> score = scoreDao.findPlayerScoreByGameApiId(dbGame.getId());
            System.out.println("I am the database gameId: " + dbGame.getId() + " I am the api gameId: " + game.getGameId());
            if (game.getStatusGame().equals("Finished") && !dbGame.getFinished()){
                System.out.println("I am the Api's game status: " + game.getStatusGame() + "I am the databases game status" + dbGame.getFinished());
                System.out.println("My score size is: " + score.size());
                if (score.size() == 0){
                    scoreMaker.generateScorecard(dbGame.getId());
                }
                dbGame.setFinished(true);
                System.out.println("Save v");
                gameDao.save(dbGame);
                System.out.println("Save ^");
            }
        }
    }
}
