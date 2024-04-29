package com.garden.alanni.network.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

/**
 * @author 吴宇伦
 */
public class UdpClient {
    public static void main(String[] args) throws IOException {
        String targetUpdHost = "localhost";
        int targetUdpPort = 8088;
        DatagramSocket datagramSocket = new DatagramSocket();
        while (true) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String message = bufferedReader.readLine();
            byte[] contents = message.getBytes(StandardCharsets.UTF_8);
            InetAddress udpInetAddress = InetAddress.getByName(targetUpdHost);
            DatagramPacket datagramPacket = new DatagramPacket(contents, contents.length, udpInetAddress, targetUdpPort);
            datagramSocket.send(datagramPacket);
        }
    }
}
