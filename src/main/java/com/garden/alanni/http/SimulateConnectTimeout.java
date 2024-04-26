package com.garden.alanni.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author 吴宇伦
 */
public class SimulateConnectTimeout {
    public static void main(String[] args) throws IOException, InterruptedException {
//        int retry = 1;
//        int connectTimes = 10000;
//        while (retry < connectTimes) {
//        retry++;
//        }
        Socket socket = new Socket();
        InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8080);
        socket.connect(inetSocketAddress);
        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String res = reader.readLine();
        System.out.println(res);
        socket.close();
    }
}
