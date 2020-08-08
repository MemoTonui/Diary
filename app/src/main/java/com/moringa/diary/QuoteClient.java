package com.moringa.diary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import retrofit2.Retrofit;

public class QuoteClient {


    public static class QuoteOfTheDay {

        private static Retrofit retrofit = null;

        public static QuoteInterface getClient() throws MalformedURLException {
            URL url = new URL("https://quotes.rest/qod");

            try {
                //make connection
                HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
                urlc.setRequestMethod("GET");
                // set the content type
                urlc.setRequestProperty("Content-Type", "application/json");
                urlc.setRequestProperty("X-TheySaidSo-Api-Secret", "YelpApiKey");
                System.out.println("Connect to: " + url.toString());
                urlc.setAllowUserInteraction(false);
                urlc.connect();

                //get result
                BufferedReader br = new BufferedReader(new InputStreamReader(urlc.getInputStream()));
                String l = null;
                while ((l = br.readLine()) != null) {
                    System.out.println(l);
                }
                br.close();
            } catch (Exception e) {
                System.out.println("Error occured");
                System.out.println(e.toString());
            }
return retrofit.create(QuoteInterface.class);
        }

    }
}
