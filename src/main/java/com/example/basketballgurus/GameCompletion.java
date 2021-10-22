package com.example.basketballgurus;

import com.example.basketballgurus.RestModels.GameModel;
import com.example.basketballgurus.models.Game;
import com.example.basketballgurus.models.PlayerScore;
import com.example.basketballgurus.repositories.GameRepository;
import com.example.basketballgurus.repositories.PlayerScoreRepository;
import com.example.basketballgurus.repositories.TeamRepository;
import com.example.basketballgurus.services.GameCompletionService;
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
    private final TeamRepository teamDao;
    private final ScoreMaker scoreMaker;
    private final PlayerScoreRepository scoreDao;
    private final ScheduleMaker scheduleMaker;

    public GameCompletion(GameRepository gameDao, TeamRepository teamDao, ScoreMaker scoreMaker, PlayerScoreRepository scoreDao, ScheduleMaker scheduleMaker) {
        this.scoreDao = scoreDao;
        this.gameDao = gameDao;
        this.teamDao = teamDao;
        this.scoreMaker = scoreMaker;
        this.scheduleMaker = scheduleMaker;
    }

    @Override
    @Scheduled(cron = "0 */15 * * * *")
    public void checkStatus() throws IOException, ParseException {

        ArrayList<GameModel> weeksGames = scheduleMaker.getGames();

        for(GameModel game : weeksGames){
            Game dbGame = gameDao.getById(game.gameId);
            List<PlayerScore> score = scoreDao.findPlayerScoreByGameApiId(dbGame.getId());
            if (game.getStatusGame().equals("Finished") && !dbGame.getFinished()){
                if (score.size() == 0){
                    scoreMaker.generateScorecard(dbGame.getId());
                }
                dbGame.setFinished(true);
                gameDao.save(dbGame);
            }
        }
    }
}
