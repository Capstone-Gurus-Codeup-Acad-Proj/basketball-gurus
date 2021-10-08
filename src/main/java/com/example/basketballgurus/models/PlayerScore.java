package com.example.basketballgurus.models;

public class PlayerScore {

    private int id;
    private int score;
    private int gameId;

    public PlayerScore() {
    }

    public PlayerScore(int id, int score, int game_id) {
        this.id = id;
        this.score = score;
        this.gameId = game_id;
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

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
}
