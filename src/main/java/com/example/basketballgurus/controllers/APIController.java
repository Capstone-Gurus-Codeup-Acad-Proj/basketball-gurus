package com.example.basketballgurus.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.MessageFormat;


public class APIController {

    @Value("${NBA_API_KEY}")
    private String apiKey;

    @RequestMapping(path = "/keys.js", produces = "application/javascript")
    @ResponseBody
    public String apiKey() {
        System.out.println(apiKey);
        return "const ApiKey = " + apiKey;

    }
}
