package com.dennym55.streams.topic22_real_interview_questions;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import static com.dennym55.streams.support.PracticeData.words;

/**
 * Custom collector.
 *
 * <p>Input: [Apple, box, level, Apex, stream, box, x, radar].
 * Expected output: count, total characters, longest word and average length.
 *
 * <p>Source: questions.md - original question 179. Improved to demonstrate the supplier,
 * accumulator, combiner, finisher, characteristics, and parallel equivalence.
 */
public class Q179_CustomCollector {

    public record WordStatistics(long count, long totalCharacters, String longest, double averageLength) {}

    private static final class Accumulator {
        private long count;
        private long totalCharacters;
        private String longest = "";

        private void add(String word) {
            count++;
            totalCharacters += word.length();
            addLongestOnly(word);
        }

        private Accumulator merge(Accumulator other) {
            count += other.count;
            totalCharacters += other.totalCharacters;
            addLongestOnly(other.longest);
            return this;
        }

        private void addLongestOnly(String candidate) {
            if (candidate.length() > longest.length()
                    || (candidate.length() == longest.length() && candidate.compareTo(longest) < 0)) {
                longest = candidate;
            }
        }

        private WordStatistics finish() {
            return new WordStatistics(count, totalCharacters, longest,
                    count == 0 ? 0.0 : (double) totalCharacters / count);
        }
    }

    public static Collector<String, ?, WordStatistics> wordStatisticsCollector() {
        Supplier<Accumulator> supplier = Accumulator::new;
        BiConsumer<Accumulator, String> accumulator = Accumulator::add;
        BinaryOperator<Accumulator> combiner = Accumulator::merge;
        Function<Accumulator, WordStatistics> finisher = Accumulator::finish;

        // The result is encounter-order independent. IDENTITY_FINISH is absent because A != R.
        return Collector.of(supplier, accumulator, combiner, finisher,
                Collector.Characteristics.UNORDERED);
    }

    public static WordStatistics summarize(List<String> input) {
        return input.stream().collect(wordStatisticsCollector());
    }

    public static void main(String[] args) {
        List<String> input = words();
        WordStatistics sequential = summarize(input);
        WordStatistics parallel = input.parallelStream().collect(wordStatisticsCollector());

        System.out.println("Input: " + input);
        System.out.println("Sequential output: " + sequential);
        System.out.println("Parallel output: " + parallel);
        System.out.println("Characteristics: " + wordStatisticsCollector().characteristics());
        System.out.println("Pitfall: the combiner must merge partial states correctly; parallel streams may invoke it many times.");
    }
}
