package com.example.basketballgurus.models;

import java.util.Date;

public class League {

    private int id;
    private int ownerId;
    private Date startDate;
    private Date endDate;
    private String leagueName;
    private String leagueDifficulty;
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
