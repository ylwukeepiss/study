package com.garden.alanni.network.http;

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
        // 2024-04-27 服务端接收到客户端请求后 输出数据前  sleep timed > socket timed out 会抛出read timed out exception
        // 这说明 read timed out 指的是 一次http交互中 数据的读写需在 socket timed out 内完成
        // timed - 定时
        String host = "127.0.0.1";
        int port = 8080;
        Socket socket = new Socket(host, port);
        socket.setSoTimeout(3000);
        InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String res = reader.readLine();
        System.out.println("收到服务端响应: " + res);
        socket.close();
    }
}
