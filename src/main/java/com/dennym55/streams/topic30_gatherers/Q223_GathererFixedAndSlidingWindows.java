package com.dennym55.streams.topic30_gatherers;

import java.util.List;
import java.util.stream.Gatherers;

/**
 * Q223: Use Java 24+ Gatherers.windowFixed() and windowSliding() to form immutable windows.
 *
 * <p>Input: [1, 2, 3, 4, 5], window size 3.
 * Expected output: fixed [[1,2,3],[4,5]]; sliding [[1,2,3],[2,3,4],[3,4,5]].
 */
public class Q223_GathererFixedAndSlidingWindows {

    public record Windows<T>(List<List<T>> fixed, List<List<T>> sliding) {}

    public static <T> Windows<T> windows(List<T> input, int size) {
        List<List<T>> fixed = input.stream().gather(Gatherers.windowFixed(size)).toList();
        List<List<T>> sliding = input.stream().gather(Gatherers.windowSliding(size)).toList();
        return new Windows<>(fixed, sliding);
    }

    public static void main(String[] args) {
        List<Integer> input = List.of(1, 2, 3, 4, 5);
        System.out.println("Input: " + input + ", window size: 3");
        System.out.println("Output: " + windows(input, 3));
        System.out.println("Pitfall: very large windows can retain substantial memory, and produced windows are unmodifiable.");
    }
}
