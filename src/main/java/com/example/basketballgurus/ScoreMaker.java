package com.example.basketballgurus;

import com.example.basketballgurus.RestModels.GameModel;
import com.example.basketballgurus.RestModels.GameStatsModel;
import com.example.basketballgurus.models.Player;
import com.example.basketballgurus.models.PlayerScore;
import com.example.basketballgurus.repositories.PlayerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ScoreMaker {
    private final PlayerRepository playerDao = new PlayerRepository() {

        @Override
        public List<Player> findAll() {
            return null;
        }

        @Override
        public List<Player> findAll(Sort sort) {
            return null;
        }

        @Override
        public Page<Player> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public List<Player> findAllById(Iterable<Long> longs) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Long aLong) {

        }

        @Override
        public void delete(Player entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Long> longs) {

        }

        @Override
        public void deleteAll(Iterable<? extends Player> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends Player> S save(S entity) {
            return null;
        }

        @Override
        public <S extends Player> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends Player> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends Player> List<S> saveAllAndFlush(Iterable<S> entities) {
            return null;
        }

        @Override
        public void deleteAllInBatch(Iterable<Player> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Long> longs) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public Player getOne(Long aLong) {
            return null;
        }

        @Override
        public Player getById(Long aLong) {
            return null;
        }

        @Override
        public <S extends Player> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends Player> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends Player> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public <S extends Player> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends Player> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends Player> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public Optional<Player> findById(Long id) {
            return null;
        }

        @Override
        public boolean existsById(Long aLong) {
            return false;
        }
    };

    public ScoreMaker() {}

    public ArrayList<PlayerScore> generateScorecard(int gameId) throws IOException {

        GameStats gameStats = new GameStats();
        ArrayList<String> json = gameStats.getStats(gameId);

        ArrayList<GameStatsModel> statsList = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        Date date = getGameDate(gameId);

        ArrayList<PlayerScore> scores = new ArrayList<>();


        for (String s : json) {

            GameStatsModel stats = mapper.readValue(s, GameStatsModel.class);
//            List<Player> allPlayers = playerDao.findById(stats.playerId);

            Player player = new Player(stats.playerId);
// Needs an actual player model, and need to put points into database, and need to have a function that does this on a schedule
            PlayerScore scorecard = new PlayerScore(0, player, generateFantasyPoints(stats), date);
            scores.add(scorecard);

        }

        return scores;

    }

    public Date getGameDate(int gameId) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        Config config = new Config();

        try {

            HttpGet request = new HttpGet("https://api-nba-v1.p.rapidapi.com/games/gameId/" + gameId);

            // add request headers
            request.addHeader("x-rapidapi-key", config.getApiKey());
            request.addHeader("x-rapidapi-host", "api-nba-v1.p.rapidapi.com");

            CloseableHttpResponse response = httpClient.execute(request);
            System.out.println(response);

            try {


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
            } finally {
                response.close();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } finally {
            httpClient.close();
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
        total += stats.steals * 3;
        total += stats.blocks * 3;
        total -= stats.turnovers;

        return total;


    }

    public static void main(String[] args) throws IOException {
        ScoreMaker sm = new ScoreMaker();
        System.out.println(sm.generateScorecard(10842));

    }





}
