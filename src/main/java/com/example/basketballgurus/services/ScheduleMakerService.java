package com.example.basketballgurus.services;

import java.io.IOException;
import java.text.ParseException;

public interface ScheduleMakerService {

    public void generateGames(int year) throws IOException, ParseException;

}
