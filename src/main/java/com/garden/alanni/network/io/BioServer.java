package com.garden.alanni.network.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 吴宇伦
 */
public class BioServer {
    public static AtomicInteger clientCnt = new AtomicInteger(0);
    private static final AtomicInteger nextThreadId = new AtomicInteger(0);
    private static final ExecutorService executorService  = new ThreadPoolExecutor(8,
            8,
            1,
            TimeUnit.MINUTES,
            new LinkedBlockingQueue<>(),
            runnable -> new Thread(runnable, "BioServerThread" + nextThreadId.getAndIncrement()));

    public static void main(String[] args) {
        new Thread(() -> {
            int cnt = 8;
            while (cnt-- > 0) {
                bioClient();
            }
        }).start();
        try {
            ServerSocket serverSocket = new ServerSocket(8888);

            System.out.println("启动BioServer");
            while (true) {
                final Socket socket = serverSocket.accept();
                executorService.submit(() -> handler(socket));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void handler(Socket socket) {
        try {
            long id = Thread.currentThread().getId();
            System.out.println("线程id :" + id + " 线程名称" + Thread.currentThread().getName());
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[1024];
            int read = inputStream.read(buffer);
            do {
                System.out.println(new String(buffer, 0, read));
                read = inputStream.read(buffer);
            } while (read != -1);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void bioClient() {
        try {
            Thread.sleep(3000);
            Socket socket = new Socket("localhost", 8888);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(("hello" + clientCnt.getAndIncrement()).getBytes());
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
