package com.example.basketballgurus.models;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="scores")
public class PlayerScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int player_id;
    @Column(nullable = false, length = 100)
    private int score;
    @Column(nullable = false, length = 100)
    private Date gameDate;

    public PlayerScore() {
    }

    public PlayerScore(int id, int player_id, int score, Date gameDate) {
        this.id = id;
        this.player_id = player_id;
        this.score = score;
        this.gameDate = gameDate;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
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

    public Date getGameDate() {
        return gameDate;
    }

    public void setGameDate(Date game_id) {
        this.gameDate = game_id;
    }
}
