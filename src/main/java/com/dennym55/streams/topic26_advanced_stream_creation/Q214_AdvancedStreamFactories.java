package com.dennym55.streams.topic26_advanced_stream_creation;

import java.util.List;
import java.util.stream.Stream;

/**
 * Q214: Create streams with Stream.ofNullable(), Stream.concat(), Stream.Builder, and the
 * three-argument Stream.iterate(seed, hasNext, next).
 *
 * <p>Input: nullable value null, lists [A, B] and [C], builder values [x, y], seed 1.
 * Expected output: [], [A, B, C], [x, y], and [1, 2, 4, 8, 16].
 */
public class Q214_AdvancedStreamFactories {

    public record CreationResults(
            List<String> nullable,
            List<String> concatenated,
            List<String> built,
            List<Integer> boundedIteration) {}

    public static CreationResults create(String nullableValue) {
        List<String> nullable = Stream.ofNullable(nullableValue).toList();
        List<String> concatenated = Stream.concat(Stream.of("A", "B"), Stream.of("C")).toList();

        Stream.Builder<String> builder = Stream.builder();
        builder.add("x").add("y");
        List<String> built = builder.build().toList();

        // Unlike iterate(seed, next), this overload is finite without requiring limit().
        List<Integer> bounded = Stream.iterate(1, value -> value <= 16, value -> value * 2).toList();
        return new CreationResults(nullable, concatenated, built, bounded);
    }

    public static void main(String[] args) {
        CreationResults output = create(null);
        System.out.println("Input nullable value: null");
        System.out.println("Output: " + output);
        System.out.println("Pitfall: a Stream.Builder cannot accept more elements after build() has been called.");
    }
}
