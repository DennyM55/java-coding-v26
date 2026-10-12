package com.dennym55.streams.topic21_parallel_streams;

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
 * Thread safety.
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: questions.md - original question 141
 */
public class Q141_ThreadSafety {
    public static void main(String[] args) {
        System.out.println("Question: Thread safety.");
        System.out.println("Source: questions.md - original question 141");
        System.out.println("Input: deterministic sample data");

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        Object result = switch (141) {
            case 141 -> { AtomicInteger safe = new AtomicInteger(); IntStream.range(0, 1_000).parallel().forEach(i -> safe.incrementAndGet()); yield safe.get(); }
            default -> throw new IllegalStateException("Unexpected question");
        };
        System.out.println("Output: " + result);
    }
}
