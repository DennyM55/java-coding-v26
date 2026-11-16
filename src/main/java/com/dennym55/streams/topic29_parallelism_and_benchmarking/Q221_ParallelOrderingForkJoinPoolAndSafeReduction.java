package com.dennym55.streams.topic29_parallelism_and_benchmarking;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.IntStream;

/**
 * Q221: Demonstrate parallel encounter ordering, worker threads, a custom ForkJoinPool, and a
 * safe associative reduction without shared mutable state.
 *
 * <p>Input: integers 1 through 10,000.
 * Expected output: ordered prefix [1..10], sum 50,005,000, and worker-thread names.
 */
public class Q221_ParallelOrderingForkJoinPoolAndSafeReduction {

    public record ParallelResult(List<Integer> orderedPrefix, long sum, Set<String> workerThreads) {}

    public static ParallelResult process(int upperInclusive, int parallelism) {
        List<Integer> ordered = new ArrayList<>();
        IntStream.rangeClosed(1, Math.min(10, upperInclusive))
                .parallel()
                .forEachOrdered(ordered::add); // forEachOrdered preserves encounter order.

        ForkJoinPool pool = new ForkJoinPool(parallelism);
        try {
            return pool.submit(() -> {
                Set<String> threads = new ConcurrentSkipListSet<>();
                long sum = IntStream.rangeClosed(1, upperInclusive)
                        .parallel()
                        .peek(ignored -> threads.add(Thread.currentThread().getName()))
                        .mapToLong(value -> value)
                        .sum(); // Associative reduction: no shared counter.
                return new ParallelResult(List.copyOf(ordered), sum, Set.copyOf(threads));
            }).get();
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting for the custom pool", exception);
        } catch (ExecutionException exception) {
            throw new IllegalStateException("Parallel computation failed", exception.getCause());
        } finally {
            pool.shutdown();
        }
    }

    public static void main(String[] args) {
        ParallelResult output = process(10_000, 2);
        System.out.println("Input: integers 1..10,000, custom parallelism 2");
        System.out.println("Output: " + output);
        System.out.println("Pitfall: forEach() is not encounter-ordered; use forEachOrdered() only when order is required.");
        System.out.println("Pitfall: custom pools should be owned and closed explicitly; never resize the common pool globally in library code.");
    }
}
