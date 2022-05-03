package cn.happymaya.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;

public class StampedLockMap<K, V> {

    Map<K, V> map = new HashMap<>();

    StampedLock lock = new StampedLock();

    public void put(K k, V v) {
        long stamp = lock.writeLock();
        try {
            map.put(k, v);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public V get(K k) {
        long stamp = lock.tryOptimisticRead();
        V v = map.get(k);
        if (!lock.validate(stamp)) {
            stamp = lock.readLock();
            try {
                v = map.get(k);
            } finally {
                lock.unlockRead(stamp);
            }
        }
        return v;
    }

    public int size() {
        return 0;
    }

}
