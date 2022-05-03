package cn.happymaya.thread.courrent;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSafeDateFormat {

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        final ThreadSafeDateFormat threadSafeDateFormat = new ThreadSafeDateFormat();
        ExecutorService executor = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executor.submit(() -> {
                try {
                    synchronized (threadSafeDateFormat) {
                        System.out.println(threadSafeDateFormat.format.parse("2020-07-25 08:56:40"));
                    }
                } catch (ParseException e) {
                    throw new IllegalStateException();
                }
            });
        }
        executor.shutdown();
    }

}
