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
    private int player_id;
    @Column(nullable = false)
    private int roster_id;
    @Column()
    private Date weekDate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(int player_id) {
        this.player_id = player_id;
    }

    public int getRoster_id() {
        return roster_id;
    }

    public void setRoster_id(int roster_id) {
        this.roster_id = roster_id;
    }

    public Date getWeekDate() {
        return weekDate;
    }

    public void setWeekDate(Date weekDate) {
        this.weekDate = weekDate;
    }
}