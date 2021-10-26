package com.example.basketballgurus;

import com.example.basketballgurus.repositories.GameRepository;
import com.example.basketballgurus.repositories.RosterRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

@org.springframework.stereotype.Controller
public class Controller {


    private final RosterScoreCalculator rsc;
    private final RosterRepository rosterDao;

    public Controller(GameRepository gc, RosterScoreCalculator rsc, RosterRepository rosterDao) {
        this.rsc = rsc;
        this.rosterDao = rosterDao;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() throws IOException, ParseException {

//        Date date = new

//        System.out.println(rsc.getHistoricalScore(rosterDao.getById(1), ));


        return "";
    }

}

