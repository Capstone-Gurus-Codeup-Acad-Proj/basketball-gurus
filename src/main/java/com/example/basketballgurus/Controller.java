package com.example.basketballgurus;

import com.example.basketballgurus.models.League;
import com.example.basketballgurus.repositories.LeaguesRepository;
import com.example.basketballgurus.repositories.RosterRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.ParseException;

@org.springframework.stereotype.Controller
public class Controller {


    private final RosterScoreCalculator rsc;
    private final RosterRepository rosterDao;
    private final LeaguesRepository leagueDao;
    private final LeagueScoreCalculator lsc;
    private final PlayerPrice pp;

    public Controller(RosterScoreCalculator rsc, RosterRepository rosterDao, LeaguesRepository leagueDao, LeagueScoreCalculator lsc, PlayerPrice pp) {
        this.rsc = rsc;
        this.rosterDao = rosterDao;
        this.leagueDao = leagueDao;
        this.lsc = lsc;
        this.pp = pp;
    }

    @GetMapping("/setPrice")
    @ResponseBody
    public String hello() throws IOException, ParseException {

        pp.createPlayerPrice();


        return "";
    }

}

