package cn.happymaya.thread.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RWLockMap<K, V> {

    Map<K, V> map = new HashMap<>();

    ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(K k, V v) {
        lock.writeLock().lock();
        try {
            map.put(k, v);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public V get(K k) {
        lock.readLock().lock();
        try {
            return map.get(k);
        } finally {
            lock.readLock().unlock();
        }
    }

    public int size() {
        lock.readLock().lock();
        try {
            return map.size();
        } finally {
            lock.readLock().unlock();
        }
    }

}
