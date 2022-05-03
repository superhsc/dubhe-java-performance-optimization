package cn.happymaya.javarules.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NIO {

    static boolean stop = false;

    public static void main(String[] args) {
        int connectionNum = 0;
        int port = 8888;
        ExecutorService service = Executors.newCachedThreadPool();

        try {

            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress("localhost", port));

            Selector selector = Selector.open();
            serverSocketChannel.register(selector, serverSocketChannel.validOps());

            while (!stop) {
                if (10 == connectionNum) {
                    stop = true;
                }
                int num = selector.select();
                if (num == 0) {
                    continue;
                }
                Iterator<SelectionKey> events = selector.selectedKeys().iterator();
                while (events.hasNext()) {
                    SelectionKey event = events.next();

                    if (event.isAcceptable()) {
                        SocketChannel socketChannel = serverSocketChannel.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        connectionNum ++ ;
                    } else if (event.isReadable()) {
                        try {
                            SocketChannel socketChannel = (SocketChannel) event.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            int size = socketChannel.read(byteBuffer);
                            if (-1 == size) {
                                socketChannel.close();
                            }
                            String result = new String(byteBuffer.array()).trim();
                            ByteBuffer wrap = ByteBuffer.wrap(("PONG: " + result).getBytes(StandardCharsets.UTF_8));
                            socketChannel.write(wrap);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            service.shutdown();
                            serverSocketChannel.close();
                        }
                    }
                    events.remove();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
