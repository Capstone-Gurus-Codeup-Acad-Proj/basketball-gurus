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
    @Column(name = "roster_id", nullable = false)
    private int rosterId;
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    public RosterPlayer() {
    }

    public RosterPlayer(int id, int player_id, int team_id, Boolean isActive) {
        this.id = id;
        this.playerId = player_id;
        this.rosterId = team_id;
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

    public int getRosterId() {
        return rosterId;
    }

    public void setRosterId(int rosterId){
        this.rosterId = rosterId;
    }

    public Boolean getActive () {
        return isActive;
    }

    public void setActive (Boolean active){
        isActive = active;
    }

}