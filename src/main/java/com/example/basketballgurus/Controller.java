package com.example.basketballgurus;

import com.example.basketballgurus.services.ScoreMakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

@org.springframework.stereotype.Controller
public class Controller {

    private final ScoreMakerService sm;

    public Controller(ScoreMakerService sm) {
        this.sm = sm;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() throws IOException {


        sm.generateScorecard(10842);

        return "";
    }

}
