package com.garden.alanni.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @author 吴宇伦
 */
public class SimulateReadTimeout {
    public static void main(String[] args) throws IOException {
        String host = "127.0.0.1";
        int port = 8080;
        Socket socket = new Socket(host, port);
        socket.setSoTimeout(1000);
        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String res = reader.readLine();
        System.out.println("收到服务端响应: " + res);
        socket.close();
    }
}
