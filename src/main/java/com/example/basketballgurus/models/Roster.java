package com.example.basketballgurus.models;

import javax.persistence.*;

@Entity
@Table(name="rosters")
public class Roster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false, length = 100)
    private int leagueId;
    @Column(nullable = false, length = 100)
    private int userId;
    @Column(nullable = false, length = 100)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String team) {
        this.name = team;
    }

    public int getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(int teamId) {
        this.leagueId = teamId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
