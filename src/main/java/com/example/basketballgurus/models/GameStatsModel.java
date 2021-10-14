package com.example.basketballgurus.models;

// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString), Root.class); */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
public class GameStatsModel {

    @Getter @Setter
    public String gameId;
    @Getter @Setter
    public String teamId;
    @Getter @Setter
    public String points;
    @Getter @Setter
    public String pos;
    @Getter @Setter
    public String min;
    @Getter @Setter
    public String fgm;
    @Getter @Setter
    public String fga;
    @Getter @Setter
    public String fgp;
    @Getter @Setter
    public String ftm;
    @Getter @Setter
    public String fta;
    @Getter @Setter
    public String ftp;
    @Getter @Setter
    public String tpm;
    @Getter @Setter
    public String tpa;
    @Getter @Setter
    public String tpp;
    @Getter @Setter
    public String offReb;
    @Getter @Setter
    public String defReb;
    @Getter @Setter
    public String totReb;
    @Getter @Setter
    public String assists;
    @Getter @Setter
    public String pFouls;
    @Getter @Setter
    public String steals;
    @Getter @Setter
    public String turnovers;
    @Getter @Setter
    public String blocks;
    @Getter @Setter
    public String plusMinus;
    @Getter @Setter
    public String playerId;

}
