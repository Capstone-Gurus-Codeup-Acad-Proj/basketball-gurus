package com.example.basketballgurus.RestModels;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

public class GameModel {

    @Getter @Setter
    public String seasonYear;
    @Getter @Setter
    public String league;
    @Getter @Setter
    public String gameId;
    @Getter @Setter
    public Date startTimeUTC;
    @Getter @Setter
    public Date endTimeUTC;
    @Getter @Setter
    public String arena;
    @Getter @Setter
    public String city;
    @Getter @Setter
    public String country;
    @Getter @Setter
    public String clock;
    @Getter @Setter
    public String gameDuration;
    @Getter @Setter
    public String currentPeriod;
    @Getter @Setter
    public String halftime;
    @Getter @Setter
    @JsonProperty("EndOfPeriod")
    public String endOfPeriod;
    @Getter @Setter
    public String seasonStage;
    @Getter @Setter
    public String statusShortGame;
    @Getter @Setter
    public String statusGame;

}
