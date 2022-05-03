package cn.happymaya.thread.lock;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@Fork(2)
@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 1)
@Measurement(iterations = 5, time = 1)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Mode.Throughput)
public class SynchronizedNormalBenchmark {
    public static class Test {
        public synchronized void doSth() {
        }

        public void doSthCommon() {
        }
    }

    Test test = new Test();


    @Benchmark
    public void synchronizedJob() {
        test.doSth();
    }

    @Benchmark
    public void noSynchronizedJob() {
        test.doSthCommon();
    }

    public static void main(String[] args) throws Exception{
        Options opts = new OptionsBuilder()
                .include(SynchronizedNormalBenchmark.class.getSimpleName())
                .resultFormat(ResultFormatType.CSV)
                .build();

        new Runner(opts).run();
    }
}