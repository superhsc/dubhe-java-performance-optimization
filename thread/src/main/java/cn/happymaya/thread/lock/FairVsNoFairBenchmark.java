package cn.happymaya.thread.lock;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

@Fork(2)
@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.Throughput)
public class FairVsNoFairBenchmark {

    ReentrantLock noFair = new ReentrantLock(false);
    ReentrantLock fair = new ReentrantLock(true);

    @Benchmark
    @Threads(20)
    public void fair(Blackhole bh) {
        fair.lock();
        try {

        } finally {
            fair.unlock();
        }
    }

    @Benchmark
    @Threads(20)
    public void noFair(Blackhole bh) {
        noFair.lock();
        try {

        } finally {
            noFair.unlock();
        }
    }

    public static void main(String[] args) throws Exception {
        Options options = new OptionsBuilder()
                .include(FairVsNoFairBenchmark.class.getSimpleName())
                .resultFormat(ResultFormatType.CSV)
                .build();

        new Runner(options).run();
    }

}
