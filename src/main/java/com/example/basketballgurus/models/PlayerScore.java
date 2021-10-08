package com.example.basketballgurus.models;

public class PlayerScore {

    private int id;
    private int score;
    private int game_id;

    public PlayerScore() {
    }

    public PlayerScore(int id, int score, int game_id) {
        this.id = id;
        this.score = score;
        this.game_id = game_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }
}
