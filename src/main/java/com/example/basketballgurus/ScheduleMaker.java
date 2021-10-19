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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class ScheduleMaker implements ScheduleMakerService {

    private final GameRepository gameDao;
    private final TeamRepository teamDao;

    public ScheduleMaker(GameRepository gameDao, TeamRepository teamDao) {
        this.gameDao = gameDao;
        this.teamDao = teamDao;
    }

    public ArrayList<GameModel> getGames(int year) throws IOException, ParseException {
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

    public ArrayList<GameModel> truncateString(String jsonString) throws JsonProcessingException, ParseException {
        int start = jsonString.indexOf("games\":[{");
        int end = jsonString.length() - 3;
        System.out.println(jsonString.substring(start + 8, end));
        return createGameArray(jsonString.substring(start + 8, end));
    }

    public ArrayList<GameModel> createGameArray(String json) throws JsonProcessingException, ParseException {

        ArrayList<GameModel> arr = new ArrayList<>();

        String str = json;

        do {

            int end = str.indexOf(",\"vTeam");
            int over = str.indexOf("{\"seasonYear", 25);

            String gameStr = str.substring(0, end) + "}";

            ObjectMapper mapper = new ObjectMapper();
            GameModel game = mapper.readValue(gameStr, GameModel.class);
            Date date = convertToEST(gameStr);

            if (date != null){
                game.setStartTimeUTC(date);
            }



            if(checkDate(game)){
                int hStart = str.indexOf("hTeam") + 7;
                TeamModel vTeam =  createTeam(str.substring(str.indexOf("vTeam") + 7,str.indexOf(",\"score")) + "}");
                TeamModel hTeam = createTeam(str.substring(hStart, str.indexOf(",\"score", hStart)) + "}");
                game.setAwayTeamId(vTeam.getTeamId());
                game.setHomeTeamId(hTeam.getTeamId());
                arr.add(game);
            }
            if (over == -1){
                break;
            }else{
                str = str.substring(over);
            }
        } while (2 < 4);

        return arr;

    }

    private TeamModel createTeam(String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        TeamModel team =  mapper.readValue(json, TeamModel.class);
        System.out.println(json);
        return team;

    }

    private Boolean checkDate(GameModel game){

        LocalDate date = LocalDate.now();
        LocalDate monday = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate sunday = date.with(TemporalAdjusters.next(DayOfWeek.SUNDAY));
        Date input = game.startTimeUTC;

        LocalDate gameDate = input.toLocalDate();
        return monday.isBefore(gameDate) && sunday.isAfter(gameDate) || monday.isEqual(gameDate) || sunday.isEqual(gameDate);

    }

    private Date convertToEST(String json) throws ParseException {

        int start = json.indexOf("startTime") + 15;
        int end = json.indexOf("endTime") - 3;

        String utc = json.substring(start, end);

        DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        utcFormat.setTimeZone(TimeZone.getTimeZone("NYC"));

        try{
            java.util.Date date = utcFormat.parse(utc);
            System.out.println(utc);
            Date newDate = new Date(date.getTime());
            return newDate;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void generateGames(int year) throws IOException, ParseException {

        ArrayList<GameModel> arr = getGames(year);
        ArrayList<Game> games = new ArrayList<>();
        gameDao.deleteAll();

        for(GameModel game : arr){
            Optional<Team> hTeam = teamDao.findById(game.homeTeamId);
            Optional<Team> vTeam = teamDao.findById(game.awayTeamId);
            System.out.println(game.getStartTimeUTC());

            Game newGame = new Game(0, game.getGameId(), hTeam.get(), vTeam.get(), game.getStartTimeUTC(), false, false);

            games.add(newGame);
        }

        gameDao.saveAll(games);

    }
}
