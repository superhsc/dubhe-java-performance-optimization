package cn.happymaya.buffer;

import java.util.LinkedHashMap;
import java.util.Map;

public class LruTools extends LinkedHashMap {

    protected int capacity;

    public LruTools(int capacity) {
        super(16, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > capacity;
    }
}
