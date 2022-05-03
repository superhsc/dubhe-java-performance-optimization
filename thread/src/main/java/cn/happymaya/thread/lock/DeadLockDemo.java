package cn.happymaya.thread.lock;

public class DeadLockDemo {

    void lock1() {
        Object object1 = new Object();
        Object object2 = new Object();

        Thread t1 = new Thread(() -> {
            synchronized (object1) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (object2) {
                }
            }
        }, "deadlock-demo-1");
        t1.start();

        Thread t2 = new Thread(() -> {
            synchronized (object2) {
                synchronized (object1) {

                }
            }
        }, "deadlock-demo-2");
        t2.start();
    }

}
