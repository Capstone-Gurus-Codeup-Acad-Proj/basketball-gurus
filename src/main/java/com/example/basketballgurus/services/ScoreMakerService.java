package com.example.basketballgurus.services;


import com.example.basketballgurus.models.PlayerScore;

import java.io.IOException;
import java.util.ArrayList;

public interface ScoreMakerService {

    public ArrayList<PlayerScore> generateScorecard(int gameId) throws IOException;

}
