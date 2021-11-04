package com.example.basketballgurus;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.ArrayList;

public class GameStats {

    @Value("${API_NBA_KEY}")
    private final String apiKey;

    public GameStats(String apiKey) {
        this.apiKey = apiKey;
    }

    public ArrayList<String> getStats(int gameId) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();



        try {

            HttpGet request = new HttpGet("https://api-nba-v1.p.rapidapi.com/statistics/players/gameId/" + gameId);

            // add request headers
            request.addHeader("x-rapidApi-key", apiKey);
            request.addHeader("x-rapidApi-host", "api-nba-v1.p.rapidApi.com");

            CloseableHttpResponse response = httpClient.execute(request);
            System.out.println(response);

            try {


                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    String result = EntityUtils.toString(entity);
                    return truncateString(result);
                }

            } finally {
                response.close();
            }
        } finally {
            httpClient.close();
        }
        return null;
    }

    public ArrayList<String> truncateString(String jsonString){
        int start = jsonString.indexOf("statistics\":[");
        String arrStr = "";
        for (int i = 0; i < jsonString.length(); i++) {
            if(i > start + 12 && i < jsonString.length() - 3){
                arrStr += jsonString.charAt(i);
            }
        }
        ArrayList<String> strings = new ArrayList<>();
        return grabParts(arrStr, strings);
    }

    public ArrayList<String> grabParts(String block, ArrayList<String> arr){

            int end = block.indexOf(",{");
            if(end == -1){
                return arr;
            }else {

                StringBuilder list = new StringBuilder();
                for (int i = 0; i < end; i++) {
                    list.append(block.charAt(i));
                }
                arr.add(list.toString());
                block = block.substring(end + 1);
                return grabParts(block, arr);
            }

    }

}
