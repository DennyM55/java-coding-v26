package com.dennym55.streams.topic01_stream_basics;

import com.dennym55.streams.support.PracticeData;
import com.dennym55.streams.support.PracticeRunner;
import com.dennym55.streams.support.PracticeData.Employee;
import com.dennym55.streams.support.PracticeData.Person;
import com.dennym55.streams.support.PracticeData.Student;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.stream.*;

import static com.dennym55.streams.support.PracticeData.*;
import static com.dennym55.streams.support.PracticeRunner.*;

/**
 * Create streams using all possible methods (`of()`, `stream()`, `Arrays.stream()`, `iterate()`, `generate()`).
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: questions.md - original question 9
 */
public class Q009_CreateStreamsUsingAllPossibleMethodsOfStreamArraysStreamIterateGenerate {
    public static void main(String[] args) {
        System.out.println("Question: Create streams using all possible methods (`of()`, `stream()`, `Arrays.stream()`, `iterate()`, `generate()`).");
        System.out.println("Source: questions.md - original question 9");
        List<Integer> input = numbers();
        System.out.println("Input: " + input);

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        Object result = switch (9) {
            case 9 -> Map.of("of", Stream.of(1, 2).toList(), "collection", input.stream().limit(2).toList(),
                                "array", Arrays.stream(new int[]{1, 2}).boxed().toList(),
                                "iterate", Stream.iterate(0, n -> n + 2).limit(3).toList(),
                                "generate", Stream.generate(() -> 7).limit(3).toList());
            default -> throw new IllegalStateException("Unexpected question");
        };
        System.out.println("Output: " + result);
    }
}
