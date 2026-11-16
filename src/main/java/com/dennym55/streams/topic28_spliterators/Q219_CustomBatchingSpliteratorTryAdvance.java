package com.dennym55.streams.topic28_spliterators;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;
import java.util.stream.StreamSupport;

/**
 * Q219: Build a custom Spliterator that uses tryAdvance() to convert an ordered source into
 * fixed-size batches and declares honest characteristics.
 *
 * <p>Input: [1, 2, 3, 4, 5, 6, 7], batch size 3.
 * Expected output: [[1, 2, 3], [4, 5, 6], [7]].
 */
public class Q219_CustomBatchingSpliteratorTryAdvance {

    public static final class BatchingSpliterator<T>
            extends Spliterators.AbstractSpliterator<List<T>> {
        private final Spliterator<T> source;
        private final int batchSize;

        public BatchingSpliterator(Spliterator<T> source, int batchSize) {
            super(batchCount(source.estimateSize(), batchSize), inheritedCharacteristics(source));
            if (batchSize < 1) {
                throw new IllegalArgumentException("batchSize must be positive");
            }
            this.source = source;
            this.batchSize = batchSize;
        }

        @Override
        public boolean tryAdvance(Consumer<? super List<T>> action) {
            List<T> batch = new ArrayList<>(batchSize);
            while (batch.size() < batchSize && source.tryAdvance(batch::add)) {
                // tryAdvance consumes at most one source element per call.
            }
            if (batch.isEmpty()) {
                return false;
            }
            action.accept(List.copyOf(batch));
            return true;
        }

        @Override
        public Spliterator<List<T>> trySplit() {
            // Stateful batching across split boundaries needs extra coordination; Q220 demonstrates safe splitting.
            return null;
        }

        private static long batchCount(long size, int batchSize) {
            if (batchSize < 1) throw new IllegalArgumentException("batchSize must be positive");
            return size == Long.MAX_VALUE ? Long.MAX_VALUE : (size + batchSize - 1) / batchSize;
        }

        private static int inheritedCharacteristics(Spliterator<?> source) {
            int supported = source.characteristics() & (Spliterator.ORDERED | Spliterator.IMMUTABLE);
            return supported | Spliterator.NONNULL;
        }
    }

    public static <T> List<List<T>> batches(List<T> input, int batchSize) {
        return StreamSupport.stream(new BatchingSpliterator<>(input.spliterator(), batchSize), false)
                .toList();
    }

    public static void main(String[] args) {
        List<Integer> input = List.of(1, 2, 3, 4, 5, 6, 7);
        BatchingSpliterator<Integer> spliterator = new BatchingSpliterator<>(input.spliterator(), 3);
        List<List<Integer>> output = StreamSupport.stream(spliterator, false).toList();

        System.out.println("Input: " + input + ", batch size: 3");
        System.out.println("Output: " + output);
        System.out.println("Characteristics: " + spliterator.characteristics());
    }
}
