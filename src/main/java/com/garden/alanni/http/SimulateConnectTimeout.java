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
        // 2024-04-27
        // 在for 循环下 文件都处于TIME_WAIT状态
        // 在mac上 使用命令查看 netstat -anv | grep 8080 | grep WAIT
        int retry = 1;
        int connectTimes = 10000;
        while (retry < connectTimes) {
            retry++;
            Socket socket = new Socket();
            InetSocketAddress inetSocketAddress = new InetSocketAddress("localhost", 8080);
            socket.connect(inetSocketAddress);
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String res = reader.readLine();
            System.out.println(res);
            // 如果将socket.close() 注释掉 TIME_WAIT状态的文件会【持续更久】 但更多依赖的是 autoCloseAble 的自动回收 猜测
//            socket.close();
        }
    }
}
