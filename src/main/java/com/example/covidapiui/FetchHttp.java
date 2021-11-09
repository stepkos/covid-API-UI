package com.example.covidapiui;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class FetchHttp {
    public static String getJsonAsStr(String APIUrl) throws IOException {
        String inline = "";

        try {
            URL url = new URL(APIUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            int responseCode = connection.getResponseCode();
            if (responseCode != 200){
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            }
            else {
                Scanner scanner = new Scanner(url.openStream());
                while(scanner.hasNext()) {
                    inline = inline.concat(scanner.nextLine());
                }
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return inline;
    }
}
