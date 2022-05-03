package cn.happymaya.thread.lock;

import java.util.ArrayList;
import java.util.List;

public class LockTimeDemo {
    List<String> list = new ArrayList<>();
    final Object lock = new Object();

    public void addList(String v) {
        synchronized (lock) {
            slowMethod();
            this.list.add(v);
        }
    }

    public void slowMethod(){

    }
}

