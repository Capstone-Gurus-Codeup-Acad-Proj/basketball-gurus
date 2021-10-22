package com.example.basketballgurus.services;

import java.io.IOException;
import java.text.ParseException;

public interface GameCompletionService {

    public void checkStatus() throws IOException, ParseException;

}
