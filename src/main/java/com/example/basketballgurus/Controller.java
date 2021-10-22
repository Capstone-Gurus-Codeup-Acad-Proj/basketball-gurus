package com.example.basketballgurus;

import com.example.basketballgurus.models.Game;
import com.example.basketballgurus.repositories.GameRepository;
import com.example.basketballgurus.services.GameCompletionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.ParseException;

@org.springframework.stereotype.Controller
public class Controller {


    private final GameRepository gc;

    public Controller(GameRepository gc) {

        this.gc = gc;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() throws IOException, ParseException {


        Game game = gc.getById(8787);

        System.out.println(game.getFormattedStartTime());



        return "";
    }

}

