package com.dennym55.streams.topic25_advanced_functional_programming;

import java.util.ArrayList;
import java.util.List;

/**
 * Q213: How do immutability and pure functions make stream pipelines safer, and why should
 * stateful side effects inside map(), filter(), or forEach() be avoided?
 *
 * <p>Input: mutable list [-2, -1, 0, 1, 2].
 * Expected output: immutable squared positives [1, 4], while the source remains unchanged.
 */
public class Q213_ImmutabilityPureFunctionsAndSideEffectPitfalls {

    public static List<Integer> squaredPositives(List<Integer> input) {
        // The predicates and mapper are pure: their output depends only on the current element.
        return input.stream()
                .filter(value -> value > 0)
                .map(value -> value * value)
                .toList(); // Stream.toList() returns an unmodifiable result.
    }

    public static void main(String[] args) {
        List<Integer> input = new ArrayList<>(List.of(-2, -1, 0, 1, 2));
        List<Integer> before = List.copyOf(input);
        List<Integer> output = squaredPositives(input);

        System.out.println("Input before: " + before);
        System.out.println("Output: " + output);
        System.out.println("Input after: " + input);
        System.out.println("Pitfall: adding to a shared ArrayList from a parallel stream is a race; use collect() instead.");
    }
}
