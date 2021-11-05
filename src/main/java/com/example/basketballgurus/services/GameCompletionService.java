package com.example.basketballgurus.services;

import com.example.basketballgurus.RestModels.GameModel;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

public interface GameCompletionService {

    public void checkStatus(ArrayList<GameModel> weeksGames) throws IOException, ParseException;

}
