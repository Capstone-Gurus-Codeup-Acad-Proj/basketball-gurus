package com.example.basketballgurus.models;


import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="history")
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int rosterPlayerId;
    @Column(nullable = false)
    private Date weekDate;
    @Column(nullable = false)
    private Boolean wasActive;

    public History(int id, int roster_player_id, Date weekDate, Boolean wasActive) {
        this.id = id;
        this.rosterPlayerId = roster_player_id;
        this.weekDate = weekDate;
        this.wasActive = wasActive;
    }

    public Boolean getWasActive() {
        return wasActive;
    }

    public void setWasActive(Boolean wasActive) {
        this.wasActive = wasActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRosterPlayerId() {
        return rosterPlayerId;
    }

    public void setRosterPlayerId(int roster_id) {
        this.rosterPlayerId = roster_id;
    }

    public Date getWeekDate() {
        return weekDate;
    }

    public void setWeekDate(Date weekDate) {
        this.weekDate = weekDate;
    }
}