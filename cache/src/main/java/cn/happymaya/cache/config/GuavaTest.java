package cn.happymaya.cache.config;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.ExecutionException;

public class GuavaTest {
    public static void main(String[] args) throws ExecutionException {
        LoadingCache<String, String> lc = CacheBuilder
                .newBuilder()
                .weakKeys()
                .removalListener(notification -> System.out.println(notification))
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s) throws Exception {
                        return slowMethod(s);
                    }
                });
        // 1s
        System.out.println(lc.get("a"));
        // 0s
        System.out.println(lc.get("a"));
        // 1s again
        System.out.println(lc.get("a"));
        lc.cleanUp();
    }

    static String slowMethod(String key) throws Exception {
        Thread.sleep(1000);
        return key + ".result";
    }
}
