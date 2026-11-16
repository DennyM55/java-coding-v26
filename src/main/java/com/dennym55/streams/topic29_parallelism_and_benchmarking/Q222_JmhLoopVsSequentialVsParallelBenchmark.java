package com.dennym55.streams.topic29_parallelism_and_benchmarking;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * Q222: Use JMH to compare an imperative loop, a sequential stream and a parallel stream without
 * relying on misleading one-shot System.nanoTime() measurements.
 *
 * <p>Input: one million integers.
 * Expected output: identical sums plus statistically measured average time for each strategy.
 */
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 2, time = 200, timeUnit = TimeUnit.MILLISECONDS)
@Measurement(iterations = 3, time = 200, timeUnit = TimeUnit.MILLISECONDS)
public class Q222_JmhLoopVsSequentialVsParallelBenchmark {
    private int[] values;

    @Setup(Level.Trial)
    public void setup() {
        values = IntStream.rangeClosed(1, 1_000_000).toArray();
    }

    @Benchmark
    public long loopSum() {
        long sum = 0;
        for (int value : values) sum += value;
        return sum;
    }

    @Benchmark
    public long sequentialStreamSum() {
        return Arrays.stream(values).mapToLong(value -> value).sum();
    }

    @Benchmark
    public long parallelStreamSum() {
        return Arrays.stream(values).parallel().mapToLong(value -> value).sum();
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(Q222_JmhLoopVsSequentialVsParallelBenchmark.class.getSimpleName())
                .forks(1)
                .shouldFailOnError(true)
                .build();
        new Runner(options).run();
        System.out.println("Pitfall: benchmark results depend on hardware, data size, warmup and JVM flags; never promise that parallel is faster.");
    }
}
