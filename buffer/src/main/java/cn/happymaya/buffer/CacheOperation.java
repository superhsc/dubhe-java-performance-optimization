package cn.happymaya.buffer;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

public class CacheOperation {


    public static void main(String[] args) {
        CacheBuilder
                .newBuilder()
                .build(new CacheLoader<String, String>() {

                    @Override
                    public String load(String key) throws Exception {
                        return slowMethod(key);
                    }

                });
    }

    static String slowMethod(String key) throws Exception {
        Thread.sleep(1000);
        return key + ".result";
    }

}
