package com.example.basketballgurus;

import com.example.basketballgurus.services.GameCompletionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.text.ParseException;

@org.springframework.stereotype.Controller
public class Controller {


    private final GameCompletionService gc;

    public Controller(GameCompletionService gc) {

        this.gc = gc;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() throws IOException, ParseException {


        gc.checkStatus();



        return "";
    }

}

