package cn.happymaya.javarules.parallel;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ParallelFetcher {

    final long timeout;
    final CountDownLatch latch;
    final ThreadPoolExecutor executor = new ThreadPoolExecutor(100, 200, 1,
            TimeUnit.HOURS, new ArrayBlockingQueue<>(100));
    public ParallelFetcher(int jobSize, long timeoutMill) {
        this.timeout = timeoutMill;
        this.latch = new CountDownLatch(jobSize);
    }

    public void submitJob(Runnable runnable) {
        executor.execute(() -> {
            runnable.run();
            latch.countDown();
        });
    }

    public void await() {
        try {
            this.latch.await(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            throw new IllegalStateException();
        }
    }

    public void dispose() {
        this.executor.shutdown();
    }

    public static void main(String[] args) {
        final String userId = "123";
        final SlowInterfaceMock mock = new SlowInterfaceMock();
        ParallelFetcher fetcher = new ParallelFetcher(20, 50);
        final Map<String, String> result = new HashMap<>();

        fetcher.submitJob(() -> result.put("method0", mock.method0(userId)));
        fetcher.submitJob(() -> result.put("method1", mock.method0(userId)));
        fetcher.submitJob(() -> result.put("method2", mock.method0(userId)));
        fetcher.submitJob(() -> result.put("method3", mock.method0(userId)));
        fetcher.submitJob(() -> result.put("method4", mock.method0(userId)));
        fetcher.submitJob(() -> result.put("method5", mock.method0(userId)));
        fetcher.submitJob(() -> result.put("method6", mock.method0(userId)));
        fetcher.submitJob(() -> result.put("method7", mock.method0(userId)));
        fetcher.submitJob(() -> result.put("method8", mock.method0(userId)));
        fetcher.submitJob(() -> result.put("method9", mock.method0(userId)));
        fetcher.submitJob(() -> result.put("method10", mock.method0(userId)));
        fetcher.submitJob(() -> result.put("method11", mock.method0(userId)));
        fetcher.submitJob(() -> result.put("method12", mock.method0(userId)));
        fetcher.submitJob(() -> result.put("method13", mock.method0(userId)));
        fetcher.submitJob(() -> result.put("method14", mock.method0(userId)));
        fetcher.submitJob(() -> result.put("method15", mock.method0(userId)));
        fetcher.submitJob(() -> result.put("method16", mock.method0(userId)));
        fetcher.submitJob(() -> result.put("method17", mock.method0(userId)));
        fetcher.submitJob(() -> result.put("method18", mock.method0(userId)));
        fetcher.submitJob(() -> result.put("method19", mock.method0(userId)));

        fetcher.await();

        System.out.println(fetcher.latch);
        System.out.println(result.size());
        System.out.println(result);

        fetcher.dispose();
    }
}
