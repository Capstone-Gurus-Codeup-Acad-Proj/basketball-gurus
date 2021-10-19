package com.example.basketballgurus;

import com.example.basketballgurus.services.ScheduleMakerService;
import com.example.basketballgurus.services.ScoreMakerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.ParseException;

@org.springframework.stereotype.Controller
public class Controller {

    private final ScoreMakerService sm;
    private final ScheduleMakerService schedule;

    public Controller(ScoreMakerService sm, ScheduleMakerService schedule) {
        this.sm = sm;
        this.schedule = schedule;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() throws IOException, ParseException {


        schedule.generateGames(2021);



        return "";
    }

}
