package com.example.basketballgurus.models;

import java.sql.Date;

public class Game {

    private int gameId;

    private int awayTeamId;

    private int homeTeamId;

    private Date startTime;

    public Game(int game_id, int away_team_id, int home_team_id, Date start_time) {
        this.gameId = game_id;
        this.awayTeamId = away_team_id;
        this.homeTeamId = home_team_id;
        this.startTime = start_time;
    }

    public Game() {
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(int awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public int getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(int homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }
}
