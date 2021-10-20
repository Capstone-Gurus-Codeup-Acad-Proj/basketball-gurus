package com.example.basketballgurus.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LeagueVisitorController {
    @GetMapping("/leagues/visitor")
    public String leaguePageVisitor() {
        return "leagueVisitor";
    }
}
