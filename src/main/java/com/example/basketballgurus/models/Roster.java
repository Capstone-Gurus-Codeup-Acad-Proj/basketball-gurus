package com.example.basketballgurus.models;

import javax.persistence.*;

@Entity
@Table(name="rosters")
public class Roster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 100)
    private int rosterId;
    @Column(nullable = false, length = 100)
    private String team;
    @Column(nullable = false, length = 100)
    private int teamId;
    @Column(nullable = false, length = 100)
    private int userId;
    @Column(nullable = false, length = 100)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRosterId() {
        return rosterId;
    }

    public void setRosterId(int rosterId) {
        this.rosterId = rosterId;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
