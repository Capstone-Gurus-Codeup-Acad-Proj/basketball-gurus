package com.example.basketballgurus.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.MessageFormat;


public class APIController {

    @Value("${APIKEY}")
    private String apiKey;

    @RequestMapping(path = "/keys.js", produces = "application/javascript")
    @ResponseBody
    public String apiKey() {
        System.out.println(apiKey);
//        return MessageFormat.format("const ApiKey = `{0}null", apiKey);
        return "89a8882cffmshf92bc0d749a1870p1ab611jsnc71954fcf253";
    }
}
