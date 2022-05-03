package cn.happymaya.thread.lock;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Fork(2)
@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.Throughput)
public class SynchronizedLockBenchmark {
    public static class Test {
        int i = 0;
        Lock lock = new ReentrantLock();

        public void sync() {
            synchronized (SynchronizedNormalBenchmark.class) {
                i++;
            }
        }


        public void lock() {
            try {
                lock.lock();
                i++;
            } finally {
                lock.unlock();
            }
        }
    }

    Test test = new Test();


    @Benchmark
    public void sync() {
        test.sync();
    }

    @Benchmark
    public void lock() {
        test.lock();
    }

    public static void main(String[] args) throws Exception {
        Options opts = new OptionsBuilder()
                .include(SynchronizedLockBenchmark.class.getSimpleName())
                .resultFormat(ResultFormatType.CSV)
                .build();

        new Runner(opts).run();
    }
}
