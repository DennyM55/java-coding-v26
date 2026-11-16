package com.dennym55.streams.topic25_advanced_functional_programming;

import java.util.List;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;

/**
 * Q212: How do lambda closures capture effectively-final variables, and how can a higher-order
 * function create and compose reusable stream transformations?
 *
 * <p>Input: numbers [1, 2, 3], captured offset 10.
 * Expected output: [22, 24, 26] after adding 10 and then doubling.
 */
public class Q212_LambdaClosuresEffectivelyFinalAndHigherOrderFunctions {

    public static IntUnaryOperator addOffset(int offset) {
        // offset is captured by the returned closure. Reassigning offset would make it non-effectively-final.
        return value -> value + offset;
    }

    public static Function<Integer, Integer> compose(
            Function<Integer, Integer> first,
            Function<Integer, Integer> second) {
        // A higher-order function accepts functions and returns a new function.
        return first.andThen(second);
    }

    public static List<Integer> transform(List<Integer> input, int offset) {
        IntUnaryOperator capturedAddition = addOffset(offset);
        Function<Integer, Integer> pipeline = compose(capturedAddition::applyAsInt, value -> value * 2);
        return input.stream().map(pipeline).toList();
    }

    public static void main(String[] args) {
        List<Integer> input = List.of(1, 2, 3);
        int offset = 10; // Effectively final: assigned once, so a lambda may capture it.
        List<Integer> output = transform(input, offset);

        System.out.println("Input: " + input + ", captured offset: " + offset);
        System.out.println("Output: " + output);
        System.out.println("Pitfall: mutating a captured local variable is a compile-time error; capture immutable values instead.");
    }
}
