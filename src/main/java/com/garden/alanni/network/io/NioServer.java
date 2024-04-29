package com.garden.alanni.network.io;

import org.springframework.boot.web.embedded.netty.NettyWebServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 吴宇伦
 */
public class NioServer {
    private static final AtomicInteger clientCnt = new AtomicInteger(0);
    public static void main(String[] args) {
        try {
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                nioClient();
            }).start();

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            Selector selector = Selector.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(9999));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            while (true) {
                if (selector.select(1000) == 0) {
                    System.out.println("等待了一秒，无事发生");
                    continue;
                }
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = keys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isAcceptable()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        if (socketChannel != null) {
                            socketChannel.configureBlocking(false);
                            System.out.println("客户端: " + clientCnt.getAndIncrement() + " 连接成功");
                            socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                        }
                    }
                    if (key.isReadable()) {
                        SocketChannel channel = (SocketChannel) key.channel();
                        if (channel != null) {
                            ByteBuffer buffer = (ByteBuffer) key.attachment();
                            channel.read(buffer);
                            System.out.println("from client : " + new String(buffer.array()));
                        }
                    }
//                    keys.remove(key);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void nioClient() {
        try {
            int cnt = 16;
            while (cnt-- > 0) {
                SocketChannel socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(false);
                InetSocketAddress address = new InetSocketAddress("localhost", 9999);
                if (!socketChannel.connect(address)) {
                    while (!socketChannel.finishConnect()) {
                        System.out.println("没连接上，客户端可以做做其他事");
                    }
                }
                String hello = "I am coming";
                ByteBuffer buffer = ByteBuffer.wrap(hello.getBytes());
                socketChannel.write(buffer);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
