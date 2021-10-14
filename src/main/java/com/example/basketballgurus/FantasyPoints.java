package com.example.basketballgurus;

import com.example.basketballgurus.models.GameStatsModel;
import com.example.basketballgurus.models.PlayerScore;
import com.fasterxml.jackson.databind.ObjectMapper;
import netscape.javascript.JSObject;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class FantasyPoints {


    public static void main(String[] args) throws IOException {

        GameStats gameStats = new GameStats();
        ArrayList<String> json = gameStats.getStats(10842);

        ArrayList<GameStatsModel> stats = new ArrayList<>();

        ObjectMapper mapper = new ObjectMapper();

        for (String s : json) {

            stats.add(mapper.readValue(s, GameStatsModel.class));

        }


        for (GameStatsModel obj : stats) {



        }


    }

}
