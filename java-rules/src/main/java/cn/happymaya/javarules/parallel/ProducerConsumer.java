package cn.happymaya.javarules.parallel;

import java.util.UUID;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class ProducerConsumer {
    private static final int Q_SIZE = 10;
    private LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>(Q_SIZE);
    private volatile boolean stop = false;

    Runnable producer = () -> {
        while (!stop) {
            try {
                queue.offer(UUID.randomUUID().toString(), 1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                //noop
            }
        }
    };
    Runnable consumer = () -> {
        while (!stop) {
            try {
                String value = queue.take();
                System.out.println(Thread.currentThread().getName() + " | " + value);
            } catch (InterruptedException e) {
                //noop
            }
        }
    };

    void start() {
        new Thread(producer, "Thread 1").start();
        new Thread(producer, "Thread 2").start();
        new Thread(consumer, "Thread 3").start();
        new Thread(consumer, "Thread 4").start();
    }

    public static void main(String[] args) throws Exception {
        ProducerConsumer pc = new ProducerConsumer();
        pc.start();
        Thread.sleep(1000 * 10);
        pc.stop = true;
    }
}
