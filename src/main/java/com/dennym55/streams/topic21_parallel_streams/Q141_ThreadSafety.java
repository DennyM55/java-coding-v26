package com.dennym55.streams.topic21_parallel_streams;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

/**
 * Thread safety.
 *
 * <p>Input: integers [0, 100,000) processed in parallel.
 * Expected output: the unsafe counter may lose updates; AtomicInteger and reduction both return 100,000.
 *
 * <p>Source: questions.md - original question 141. Improved with an actual race, an atomic fix,
 * and the preferred stateless reduction.
 */
public class Q141_ThreadSafety {

    public record Counts(int unsafeSharedCounter, int atomicCounter, long reductionCount) {}

    public static Counts count(int size) {
        int[] unsafe = {0};
        IntStream.range(0, size).parallel().forEach(ignored -> unsafe[0]++); // Data race.

        AtomicInteger atomic = new AtomicInteger();
        IntStream.range(0, size).parallel().forEach(ignored -> atomic.incrementAndGet());

        // Preferred: no shared mutation; partitions reduce independently and combine safely.
        long reduction = IntStream.range(0, size).parallel().count();
        return new Counts(unsafe[0], atomic.get(), reduction);
    }

    public static void main(String[] args) {
        int size = 100_000;
        Counts output = count(size);
        System.out.println("Input size: " + size);
        System.out.println("Output: " + output);
        System.out.println("Pitfall: an unsafe result can occasionally look correct; that does not remove the data race.");
    }
}
