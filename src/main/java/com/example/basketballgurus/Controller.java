package com.example.basketballgurus;

import com.example.basketballgurus.repositories.GameRepository;
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
    private final GameRepository gameDao;

    public Controller(RosterScoreCalculator rsc, RosterRepository rosterDao, LeaguesRepository leagueDao, LeagueScoreCalculator lsc, PlayerPrice pp, GameRepository gameDao) {
        this.rsc = rsc;
        this.rosterDao = rosterDao;
        this.leagueDao = leagueDao;
        this.lsc = lsc;
        this.pp = pp;
        this.gameDao = gameDao;
    }

    @GetMapping("/setPrice")
    @ResponseBody
    public String hello() throws IOException, ParseException {

        gameDao.deleteAll();

        pp.createPlayerPrice();


        return "";
    }

}

