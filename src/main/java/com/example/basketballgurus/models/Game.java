package com.example.basketballgurus.models;
import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="weeks_games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int gameId;

    @OneToOne
    @JoinColumn(name = "home_team_id", referencedColumnName = "id")
    private Team homeTeamId;

    @OneToOne
    @JoinColumn(name = "away_team_id", referencedColumnName = "id")
    private Team awayTeamId;


    @Column(nullable = false)
    private Date startTime;

    public Game(int id, Team away_team_id, Team home_team_id, Date start_time, int gameId) {
        this.id = id;
        this.awayTeamId = away_team_id;
        this.homeTeamId = home_team_id;
        this.startTime = start_time;
        this.gameId = gameId;
    }
    public Game() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getGameId() {
        return gameId;
    }
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    public Team getAwayTeamId() {
        return awayTeamId;
    }
    public void setAwayTeamId(Team awayTeamId) {
        this.awayTeamId = awayTeamId;
    }
    public Team getHomeTeamId() {return homeTeamId;}
    public void setHomeTeamId(Team homeTeamId) {
        this.homeTeamId = homeTeamId;
    }
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }}