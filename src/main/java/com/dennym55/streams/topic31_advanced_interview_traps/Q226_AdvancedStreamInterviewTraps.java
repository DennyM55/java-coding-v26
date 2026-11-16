package com.dennym55.streams.topic31_advanced_interview_traps;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Q226: Demonstrate and fix advanced Stream interview traps involving stream reuse, null values,
 * collector mutability, and non-terminating infinite streams.
 *
 * <p>Input: ["A", null, "B"].
 * Expected output: reusable counts [3,3], non-null values [A,B], a known-mutable list, an
 * unmodifiable list and the first five even values from an infinite stream.
 */
public class Q226_AdvancedStreamInterviewTraps {

    public record TrapResults(
            List<Long> reusableCounts,
            List<String> nonNullValues,
            List<String> explicitlyMutable,
            List<String> unmodifiable,
            List<Integer> finiteFromInfinite) {}

    public static TrapResults solve(List<String> input) {
        // A supplier creates a fresh stream for each terminal operation; never cache a Stream instance.
        Supplier<Stream<String>> streams = input::stream;
        List<Long> counts = List.of(streams.get().count(), streams.get().count());

        List<String> nonNull = input.stream().flatMap(Stream::ofNullable).toList();

        // Collectors.toList() makes no mutability guarantee; request the concrete behavior you need.
        List<String> mutable = nonNull.stream().collect(Collectors.toCollection(ArrayList::new));
        mutable.add("C");
        List<String> unmodifiable = nonNull.stream().toList();

        // An infinite source requires a short-circuiting operation such as limit/findFirst/anyMatch.
        List<Integer> finite = Stream.iterate(0, value -> value + 1)
                .filter(value -> value % 2 == 0)
                .limit(5)
                .toList();
        return new TrapResults(counts, nonNull, List.copyOf(mutable), unmodifiable, finite);
    }

    public static void main(String[] args) {
        List<String> input = java.util.Arrays.asList("A", null, "B");
        System.out.println("Input: " + input);
        System.out.println("Output: " + solve(input));
        System.out.println("Pitfall: sorted(), distinct() or count() on an unbounded stream may never complete before a useful short circuit.");
    }
}
