package com.garden.alanni.http;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 吴宇伦
 */
public class WebServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 8080;
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("----接收到客户端请求-------");
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            Thread.sleep(2000);
            printStream.println("服务器响应在此！");
            printStream.close();
            socket.close();
        }
    }
}
