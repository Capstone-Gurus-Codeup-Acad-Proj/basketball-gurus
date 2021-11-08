package com.example.basketballgurus;

import com.example.basketballgurus.RestModels.GameModel;
import com.example.basketballgurus.RestModels.GameStatsModel;
import com.example.basketballgurus.models.Player;
import com.example.basketballgurus.models.PlayerScore;
import com.example.basketballgurus.repositories.GameRepository;
import com.example.basketballgurus.repositories.PlayerRepository;
import com.example.basketballgurus.repositories.PlayerScoreRepository;
import com.example.basketballgurus.services.ScoreMakerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;

@Service
class ScoreMaker implements ScoreMakerService {

    private final PlayerRepository playerDao;
    private final PlayerScoreRepository scoreDao;
    private final GameRepository gameDao;
    @Value("${NBA_API_KEY}")
    private String apiKey;

    public ScoreMaker(PlayerRepository playerDao, PlayerScoreRepository scoreDao, GameRepository gameDao) {
        this.playerDao = playerDao;
        this.scoreDao = scoreDao;
        this.gameDao = gameDao;
    }


    public void generateScorecard(int gameId) throws IOException {

        GameStats gameStats = new GameStats(apiKey);
        ArrayList<String> json = gameStats.getStats(gameId);

        ObjectMapper mapper = new ObjectMapper();

        Date date = getGameDate(gameId);

        ArrayList<PlayerScore> scores = new ArrayList<>();

        for (String s : json) {

            GameStatsModel stats = mapper.readValue(s, GameStatsModel.class);
            Optional<Player> player = playerDao.findById(stats.playerId);
            if (player.isPresent()){
                System.out.println("he lives");
                PlayerScore scorecard = new PlayerScore(0, player.get(), generateFantasyPoints(stats), gameId, date);
                scores.add(scorecard);
                System.out.println(scorecard.getScore());
                System.out.println(scorecard.getPlayerId());
            }else{
                System.out.println("he dies");
            }
        }

        scoreDao.saveAll(scores);

    }

    public Date getGameDate(int gameId) throws IOException {

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

            HttpGet request = new HttpGet("https://api-nba-v1.p.rapidapi.com/games/gameId/" + gameId);

            // add request headers
            request.addHeader("x-rapidApi-key", apiKey);
            request.addHeader("x-rapidApi-host", "api-nba-v1.p.rapidApi.com");

            CloseableHttpResponse response = httpClient.execute(request);

            try (response) {
                System.out.println(response);


                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    String result = EntityUtils.toString(entity);
                    ObjectMapper mapper = new ObjectMapper();
                    GameModel game = mapper.readValue(truncateString(result), GameModel.class);
                    return game.getStartTimeUTC();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String truncateString(String jsonString){
        int start = jsonString.indexOf("games\":[");
        int end = jsonString.indexOf(",\"vTeam");
        return jsonString.substring(start + 8, end) + "}";
    }

    public int generateFantasyPoints(GameStatsModel stats){
        int total = 0;

        total += stats.points;
        total += stats.assists * 1.5;
        total += stats.defReb * 1.5;
        total += stats.offReb * 1.5;
        total += stats.steals * 3;
        total += stats.blocks * 3;
        total -= stats.turnovers;

        return total;


    }

    public GameRepository getGameDao() {
        return gameDao;
    }
}
