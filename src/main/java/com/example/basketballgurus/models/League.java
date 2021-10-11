package com.example.basketballgurus.models;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name="leagues")
public class League {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int ownerId;
    @Column()
    private Date startDate;
    @Column()
    private Date endDate;
    @Column(nullable = false, length = 100)
    private String leagueName;
    @Column(nullable = false, length = 100)
    private String leagueDifficulty;
    @Column(nullable = false, length = 100)
    private String leaguePfp;


    public League(int id, int owner_id, Date start_Date, Date end_Date, String league_name, String league_difficulty,           String league_pfp) {
        this.id = id;
        this.ownerId = owner_id;
        this.startDate = start_Date;
        this.endDate = end_Date;
        this.leagueName = league_name;
        this.leagueDifficulty = league_difficulty;
        this.leaguePfp = league_pfp;
    }

    public League() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getLeagueDifficulty() {
        return leagueDifficulty;
    }

    public void setLeagueDifficulty(String leagueDifficulty) {
        this.leagueDifficulty = leagueDifficulty;
    }

    public String getLeaguePfp() {
        return leaguePfp;
    }

    public void setLeaguePfp(String leaguePfp) {
        this.leaguePfp = leaguePfp;
    }
}