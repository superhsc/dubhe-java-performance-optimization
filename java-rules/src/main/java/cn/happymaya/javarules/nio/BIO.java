package cn.happymaya.javarules.nio;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIO {

    static boolean stop = false;

    public static void main(String[] args) {
        int connectionNum = 0;
        int port = 8888;

        ExecutorService service = Executors.newCachedThreadPool();

        try {
            ServerSocket  serverSocket = new ServerSocket(port);
            while (!stop) {
                if (10 == connectionNum) {
                    stop = true;
                }
                Socket socket = serverSocket.accept();
                service.execute(() -> {
                    try {
                        Scanner scanner = new Scanner(socket.getInputStream());
                        PrintStream printStream = new PrintStream(socket.getOutputStream());
                        while (!stop) {
                            String s = scanner.next().trim();
                            printStream.println("PONG:" + s);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        service.shutdown();
                        try {
                            serverSocket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                });
                connectionNum++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        };


    }
}
