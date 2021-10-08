package com.example.basketballgurus.models;
import javax.annotation.processing.Generated;
import javax.persistence.*;

@Entity
@Table(name="scores")
public class PlayerScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 100)
    private int score;
    @Column(nullable = false, length = 100)
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
