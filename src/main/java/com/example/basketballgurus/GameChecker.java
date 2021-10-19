package com.example.basketballgurus;

import com.example.basketballgurus.RestModels.GameModel;
import com.example.basketballgurus.RestModels.TeamModel;
import com.example.basketballgurus.repositories.GameRepository;
import com.example.basketballgurus.repositories.TeamRepository;
import com.example.basketballgurus.services.GameCheckerService;
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
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class GameChecker implements GameCheckerService {

//    private final GameRepository gameDao;
//
//    public GameChecker(GameRepository gameDao, TeamRepository teamDao) {
//        this.gameDao = gameDao;
//    }


    @Override
    public void checkStatus() throws IOException {

        LocalDate date = LocalDate.now();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        Config config = new Config();

        HttpGet request = new HttpGet("https://api-nba-v1.p.rapidapi.com/games/date/" + date.getYear() + "-" + date.getMonthValue() + "-" + (date.getDayOfMonth() + 1));

        // add request headers
        request.addHeader("x-rapidapi-key", config.getApiKey());
        request.addHeader("x-rapidapi-host", "api-nba-v1.p.rapidapi.com");

        CloseableHttpResponse response = httpClient.execute(request);
        HttpEntity entity = response.getEntity();
        String result = EntityUtils.toString(entity);

        System.out.println(truncateString(result).size());

    }

    public ArrayList<GameModel> truncateString(String jsonString) throws JsonProcessingException {
        int start = jsonString.indexOf("games\":[{");
        int end = jsonString.length() - 3;
        System.out.println(jsonString.substring(start + 8, end));
        return createGameArray(jsonString.substring(start + 8, end));
    }

    public ArrayList<GameModel> createGameArray(String json) throws JsonProcessingException {

        ArrayList<GameModel> arr = new ArrayList<>();

        String str = json;

        for (int i = 0; i < json.length(); i++) {

            int end = str.indexOf(",\"vTeam");
            int over = str.indexOf("{\"seasonYear", 25);

            String gameStr = str.substring(0, end) + "}";

            ObjectMapper mapper = new ObjectMapper();
            GameModel game = mapper.readValue(gameStr, GameModel.class);

            arr.add(game);

            if (over == -1){
                break;
            }else{
                str = str.substring(over);
            }
        }

        return arr;

    }

    public static void main(String[] args) throws IOException {
        GameChecker gc = new GameChecker();
        gc.checkStatus();
    }
}
