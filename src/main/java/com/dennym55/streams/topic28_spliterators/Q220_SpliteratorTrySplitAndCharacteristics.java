package com.dennym55.streams.topic28_spliterators;

import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.function.IntConsumer;
import java.util.stream.StreamSupport;

/**
 * Q220: Implement trySplit() for a sized integer-range Spliterator and explain how its
 * ORDERED, SORTED, DISTINCT, SIZED, SUBSIZED, IMMUTABLE and NONNULL characteristics are used.
 *
 * <p>Input: range [0, 10).
 * Expected output: a prefix [0..4], remainder [5..9], and parallel sum 45.
 */
public class Q220_SpliteratorTrySplitAndCharacteristics {

    public static final class IntRangeSpliterator implements Spliterator.OfInt {
        private int current;
        private final int fence;

        public IntRangeSpliterator(int startInclusive, int endExclusive) {
            if (endExclusive < startInclusive) throw new IllegalArgumentException("end before start");
            current = startInclusive;
            fence = endExclusive;
        }

        @Override
        public boolean tryAdvance(IntConsumer action) {
            if (current >= fence) return false;
            action.accept(current++);
            return true;
        }

        @Override
        public OfInt trySplit() {
            int remaining = fence - current;
            if (remaining < 2) return null;
            int midpoint = current + remaining / 2;
            OfInt prefix = new IntRangeSpliterator(current, midpoint);
            current = midpoint;
            return prefix;
        }

        @Override
        public long estimateSize() {
            return fence - current;
        }

        @Override
        public int characteristics() {
            return ORDERED | SORTED | DISTINCT | SIZED | SUBSIZED | IMMUTABLE | NONNULL;
        }

        @Override
        public Comparator<? super Integer> getComparator() {
            return null; // null means natural order for a SORTED Spliterator.
        }
    }

    public static long parallelSum(int startInclusive, int endExclusive) {
        return StreamSupport.intStream(new IntRangeSpliterator(startInclusive, endExclusive), true)
                .mapToLong(value -> value)
                .sum();
    }

    public static void main(String[] args) {
        IntRangeSpliterator remainder = new IntRangeSpliterator(0, 10);
        Spliterator.OfInt prefix = remainder.trySplit();
        List<Integer> left = StreamSupport.intStream(prefix, false).boxed().toList();
        List<Integer> right = StreamSupport.intStream(remainder, false).boxed().toList();

        System.out.println("Input range: [0, 10)");
        System.out.println("Split prefix: " + left);
        System.out.println("Split remainder: " + right);
        System.out.println("Parallel sum: " + parallelSum(0, 10));
        System.out.println("Pitfall: claiming SIZED or SORTED without honoring the contract can corrupt optimizations.");
    }
}
