package cn.happymaya.javarules.parallel;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.concurrent.FastThreadLocalThread;

import java.util.UUID;

public class FastThreadLocalExample {
    void test() {
        FastThreadLocal<String> threadLocal = new FastThreadLocal<>();
        threadLocal.set(UUID.randomUUID().toString());
        System.out.println(threadLocal.get());
        threadLocal.remove();
        System.out.println(threadLocal.get());
    }

    public static void main(String[] args) {
        final FastThreadLocalExample example = new FastThreadLocalExample();
        new FastThreadLocalThread(() -> {
            example.test();
        }).start();
    }
}
