package com.garden.alanni.network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * @author 吴宇伦
 */
public class UdpServer {
    public static void main(String[] args) throws IOException {
        int port = 8088;
        DatagramSocket datagramSocket = new DatagramSocket(port);
        byte[] buffer = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(buffer, buffer.length);
        while (true) {
            System.out.println("------开始接收来自客户端的数据");
            // 在while 循环下 从控制台可以看出 receive方法是阻塞的
            datagramSocket.receive(receivePacket);
            byte[] data = receivePacket.getData();
            System.out.println("----客户端的数据：" + new String(data, 0, receivePacket.getLength()));
        }
    }
}
