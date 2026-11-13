package com.dennym55.streams.topic24_additional;

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
 * ADDITIONAL: Demonstrate stream creation, laziness, and short-circuiting
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: PRACTICE_INDEX.md - additional question 2
 */
public class Q205_ADDITIONALDemonstrateStreamCreationLazinessAndShortCircuiting {
    public static void main(String[] args) {
        System.out.println("Question: ADDITIONAL: Demonstrate stream creation, laziness, and short-circuiting");
        System.out.println("Source: PRACTICE_INDEX.md - additional question 2");
        System.out.println("Input: deterministic sample data");

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        Object result = switch (205) {
            case 205 -> { AtomicInteger calls = new AtomicInteger(); var firstOnly = Stream.of(1, 2, 3).map(x -> { calls.incrementAndGet(); return x * 2; }).limit(1).toList(); yield Map.of("result", firstOnly, "mapped elements", calls.get()); }
            default -> throw new IllegalStateException("Unexpected question");
        };
        System.out.println("Output: " + result);
    }
}
