package com.example.basketballgurus.models;

import javax.persistence.*;

@Entity
@Table(name = "roster_player")
public class RosterPlayer {
    @Id
    @Column(nullable = false)
    private int id;
    @Column(nullable = false)
    private int playerId;
    @Column(nullable = false)
    private int teamId;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    public RosterPlayer() {
    }

    public RosterPlayer(int id, int player_id, int team_id, Boolean isActive) {
        this.id = id;
        this.playerId = player_id;
        this.teamId = team_id;
        this.isActive = isActive;
    }



    public int getId () {
        return id;
    }

    public void setId ( int id){
        this.id = id;
    }

    public int getPlayer_id () {
        return playerId;
    }

    public void setPlayer_id ( int player_id){
        this.playerId = player_id;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId){
        this.teamId = teamId;
    }

    public Boolean getActive () {
        return isActive;
    }

    public void setActive (Boolean active){
        isActive = active;
    }

}