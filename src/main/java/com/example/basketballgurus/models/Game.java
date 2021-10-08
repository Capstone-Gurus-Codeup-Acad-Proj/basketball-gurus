package com.example.basketballgurus.models;

import java.sql.Date;

public class Game {

    private int game_id;

    private int away_team_id;

    private int home_team_id;

    private Date start_time;

    public Game(int game_id, int away_team_id, int home_team_id, Date start_time) {
        this.game_id = game_id;
        this.away_team_id = away_team_id;
        this.home_team_id = home_team_id;
        this.start_time = start_time;
    }

    public Game() {
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getAway_team_id() {
        return away_team_id;
    }

    public void setAway_team_id(int away_team_id) {
        this.away_team_id = away_team_id;
    }

    public int getHome_team_id() {
        return home_team_id;
    }

    public void setHome_team_id(int home_team_id) {
        this.home_team_id = home_team_id;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }
}
