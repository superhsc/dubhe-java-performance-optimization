package cn.happymaya.cache.config;

import java.util.LinkedHashMap;
import java.util.Map;

public class LRU extends LinkedHashMap {

    int capacity;

    public LRU(int capacity) {
        super(16, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > capacity;
    }

    public static void main(String[] args) {
        LRU lru = new LRU(5);
        for (int i = 0; i < 5; i++) {
            lru.put(i+1, i);
        }
        System.out.println(lru.size() + " | " + lru);
    }
}
