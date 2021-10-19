package com.example.basketballgurus;

import com.example.basketballgurus.RestModels.GameModel;
import com.example.basketballgurus.RestModels.TeamModel;
import com.example.basketballgurus.models.Game;
import com.example.basketballgurus.models.Team;
import com.example.basketballgurus.repositories.GameRepository;
import com.example.basketballgurus.repositories.TeamRepository;
import com.example.basketballgurus.services.ScheduleMakerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Date;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class ScheduleMaker implements ScheduleMakerService {

    private final GameRepository gameDao;
    private final TeamRepository teamDao;

    public ScheduleMaker(GameRepository gameDao, TeamRepository teamDao) {
        this.gameDao = gameDao;
        this.teamDao = teamDao;
    }

    public ArrayList<GameModel> getGames(int year) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        Config config = new Config();

            HttpGet request = new HttpGet("https://api-nba-v1.p.rapidapi.com/games/league/standard/" + year);

        // add request headers
        request.addHeader("x-rapidapi-key", config.getApiKey());
        request.addHeader("x-rapidapi-host", "api-nba-v1.p.rapidapi.com");

        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);
        return truncateString(result);
    }

    public ArrayList<GameModel> truncateString(String jsonString) throws JsonProcessingException {
        int start = jsonString.indexOf("games\":[{");
        int end = jsonString.length() - 3;
        ArrayList<GameModel> arr = new ArrayList<>();
        System.out.println(jsonString.substring(start + 8, end));
        return createGameArray(jsonString.substring(start + 8, end), arr);

    }

    public ArrayList<GameModel> createGameArray(String json, ArrayList<GameModel> arr) throws JsonProcessingException {

        int end = json.indexOf(",\"vTeam");
        int over = json.indexOf("{\"seasonYear", 25);
        int cutOff;

        if (over == -1){
            cutOff = 0;
        }else{
            cutOff = json.indexOf("{\"seasonYear", 25);
        }
        String list = json.substring(0, end);
        list += "}";
        ObjectMapper mapper = new ObjectMapper();
        GameModel game = mapper.readValue(list, GameModel.class);
        if (checkDate(game)){
            int hStart = json.indexOf("hTeam") + 7;
            TeamModel vTeam =  createTeam(json.substring(json.indexOf("vTeam") + 7,json.indexOf(",\"score")) + "}");
            TeamModel hTeam = createTeam(json.substring(hStart, json.indexOf(",\"score", hStart)) + "}");
            game.setAwayTeamId(vTeam.getTeamId());
            game.setHomeTeamId(hTeam.getTeamId());
            arr.add(game);
        }
        if (over == -1){
            return arr;
        } else{
            return createGameArray(json.substring(cutOff), arr);
        }
    }

    private TeamModel createTeam(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(json, TeamModel.class);

    }

    private Boolean checkDate(GameModel game){

        LocalDate date = LocalDate.now();
        LocalDate monday = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate sunday = date.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        Date input = game.startTimeUTC;



        LocalDate gameDate = input.toLocalDate();
        return monday.isBefore(gameDate) && sunday.isAfter(gameDate) || monday.isEqual(gameDate) || sunday.isEqual(gameDate);


    }

    @Override
    public void generateGames(int year) throws IOException {

        ArrayList<GameModel> arr = getGames(year);
        ArrayList<Game> games = new ArrayList<>();

        for(GameModel game : arr){
            Optional<Team> hTeam = teamDao.findById(game.homeTeamId);
            Optional<Team> vTeam = teamDao.findById(game.awayTeamId);

            Game newGame = new Game(0, game.getGameId(), hTeam.get(), vTeam.get(), game.startTimeUTC, false, false);

            games.add(newGame);
        }

        gameDao.saveAll(games);

    }

}
