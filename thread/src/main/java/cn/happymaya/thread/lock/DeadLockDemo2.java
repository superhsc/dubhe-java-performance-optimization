package cn.happymaya.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLockDemo2 {

    public static void main(String[] args) {
        Lock lock1 = new ReentrantLock();
        Lock lock2 = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            lock1.lock();
            try {
                Thread.sleep(200);
                lock2.lock();
                try {
                } finally {
                    lock2.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock1.unlock();
            }
        }, "deadlock-demo-1");

        t1.start();
        Thread t2 = new Thread(() -> {
            lock2.lock();
            try {
                lock1.lock();
                try {

                } finally {
                    lock1.unlock();
                }
            } finally {
                lock2.unlock();
            }
        }, "deadlock-demo-2");

    }
}
