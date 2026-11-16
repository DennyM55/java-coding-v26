package com.dennym55.streams.topic30_gatherers;

import java.util.List;
import java.util.Objects;
import java.util.stream.Gatherer;

/**
 * Q225: Build a custom sequential Gatherer with initializer and integrator functions that
 * collapses only consecutive duplicate values.
 *
 * <p>Input: [A, A, B, B, A, C, C].
 * Expected output: [A, B, A, C].
 */
public class Q225_CustomGathererCollapseConsecutiveDuplicates {

    private static final class RunState<T> {
        private boolean seen;
        private T previous;
    }

    public static <T> Gatherer<T, ?, T> collapseConsecutive() {
        return Gatherer.<T, RunState<T>, T>ofSequential(
                RunState::new,
                Gatherer.Integrator.ofGreedy((state, element, downstream) -> {
                    if (!state.seen || !Objects.equals(state.previous, element)) {
                        state.seen = true;
                        state.previous = element;
                        return downstream.push(element);
                    }
                    return true;
                }));
    }

    public static <T> List<T> collapse(List<T> input) {
        return input.stream().gather(collapseConsecutive()).toList();
    }

    public static void main(String[] args) {
        List<String> input = List.of("A", "A", "B", "B", "A", "C", "C");
        System.out.println("Input: " + input);
        System.out.println("Output: " + collapse(input));
        System.out.println("Pitfall: this gatherer is sequential because run state cannot be combined safely across arbitrary partition boundaries.");
    }
}
