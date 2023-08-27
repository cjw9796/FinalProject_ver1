package com.kh.myproject.store.tour;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallTourAPI {

    public static String getData(String addr) throws Exception {

        StringBuilder result = new StringBuilder();

        URL url = new URL(addr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

        String line;

        while ((line = br.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }
}