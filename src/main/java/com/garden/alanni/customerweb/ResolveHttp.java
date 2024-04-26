package com.garden.alanni.customerweb;

import org.springframework.util.StringUtils;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @date 2024-03-13
 * @author 吴宇伦
 */
public class ResolveHttp {
    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        System.out.println("server is running");
        while (true) {
            Socket accept = serverSocket.accept();
            System.out.println("connect from : " + accept.getRemoteSocketAddress());
            Thread handler = new Handler(accept);
            handler.start();
        }
    }
}

class Handler extends Thread {
    Socket socket;

    public Handler() {
    }

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = this.socket.getInputStream();
             OutputStream outputStream = this.socket.getOutputStream()) {
            handle(inputStream, outputStream);
        } catch (Exception e) {
            System.out.println("resolve-remote-input-err-" + e.getMessage());
            try {
                socket.close();
            } catch (Exception closeErrorException) {
                // do nothing but log
                System.out.println(closeErrorException.getMessage());
            }
            System.out.println("client-disconnect");
        }
    }

    private void handle(InputStream inputStream, OutputStream outputStream) throws IOException, InterruptedException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8));
        // 解析HTTP请求
        boolean requestOk = false;
        String firstLine = bufferedReader.readLine();
        if (firstLine.startsWith("GET / HTTP/1.")) {
            requestOk = true;
        }
        Map<String, String> headerParams = new HashMap<>(8);
        for (; ; ) {
            String header = bufferedReader.readLine();
            if (header.isEmpty()) {
                // 读取到空行 则退出
                break;
            }
            String[] keyValue = header.split(":");
            headerParams.put(keyValue[0], keyValue[1]);
            System.out.println(header);
        }
        System.out.println("header-params: " + headerParams.toString());
        System.out.println(requestOk ? "response OK" : "response ERROR");
        if (!requestOk) {
            // 发送错误响应
            bufferedWriter.write("HTTP/1.0 404 Not Found\r\n");
            bufferedWriter.write("Content-Length: 0\r\n");
            bufferedWriter.write("\r\n");
            bufferedWriter.flush();
        } else {
            // 发送成功响应
            InputStream is = ResolveHttp.class.getClassLoader().getResourceAsStream("static/response.html");
            BufferedReader resource = new BufferedReader(new InputStreamReader(is));
            StringBuilder data = new StringBuilder();
            while ((resource.readLine() != null)) {
                data.append(resource.readLine());
            }
            resource.close();
            int length = data.toString().getBytes(StandardCharsets.UTF_8).length;
            bufferedWriter.write("HTTP/1.1 200 OK\r\n");
            bufferedWriter.write("Content-type: text/html\r\n");
            bufferedWriter.write("Content-length: " + length + "\r\n");
            // 空行 标识 header 与 body的分隔
            bufferedWriter.write("\r\n");
            bufferedWriter.write(data.toString());
            bufferedWriter.flush();

            Thread.sleep(10000);
        }
    }
}

