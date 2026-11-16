package com.dennym55.streams.topic30_gatherers;

import java.util.List;
import java.util.stream.Gatherers;

/**
 * Q224: Contrast Gatherers.fold(), which emits one final result, with Gatherers.scan(), which
 * emits every incremental prefix result.
 *
 * <p>Input: [1, 2, 3, 4].
 * Expected output: folded sum 10; scanned running totals [1, 3, 6, 10].
 */
public class Q224_GathererFoldAndScan {

    public record FoldAndScan(int folded, List<Integer> scanned) {}

    public static FoldAndScan calculate(List<Integer> input) {
        int folded = input.stream()
                .gather(Gatherers.fold(() -> 0, Integer::sum))
                .findFirst()
                .orElse(0);
        List<Integer> scanned = input.stream()
                .gather(Gatherers.scan(() -> 0, Integer::sum))
                .toList();
        return new FoldAndScan(folded, scanned);
    }

    public static void main(String[] args) {
        List<Integer> input = List.of(1, 2, 3, 4);
        System.out.println("Input: " + input);
        System.out.println("Output: " + calculate(input));
        System.out.println("Interview note: fold is useful for ordered reductions without a valid combiner; scan exposes all prefixes.");
    }
}
