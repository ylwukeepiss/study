package com.garden.alanni.customerweb;


import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

/**
 * @author 吴宇伦
 */
public class HttpRequest {
    public static void main(String[] args) throws IOException, InterruptedException {
        String protocol = "http";
        String host = "localhost";
        int port = 8080;
        String file = "";
        URL url = new URL(protocol, host, port, file);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
//        connection.setConnectTimeout(5000);
//        connection.setReadTimeout(50000);

//        connection.setRequestProperty("Connection-Type", "timeOutDefalut");
//        connection.setDoInput(false);
//        connection.setDoOutput(false);
        connection.connect();

        InputStream resultStream = connection.getInputStream();
        InputStreamReader reader = new InputStreamReader(resultStream, StandardCharsets.UTF_8);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String response = "";
        while (StringUtils.isNotEmpty(response = bufferedReader.readLine())) {
            System.out.println(response);
        }
        Thread.sleep(20000);
    }
}
