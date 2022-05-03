package cn.happymaya.thread.lock;

import java.util.ArrayList;
import java.util.List;

public class LockLessDemo {

    List<String> list1 = new ArrayList<>();
    List<String> list2 = new ArrayList<>();

    final Object lock1 = new Object();
    final Object lock2 = new Object();

    public void addList1(String v){
        synchronized (list1) {
            this.list1.add(v);
        }
    }


    public void addList2(String v) {
        synchronized (lock2) {
            this.list2.add(v);
        }
    }

}
