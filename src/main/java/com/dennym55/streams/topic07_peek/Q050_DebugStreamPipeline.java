package com.dennym55.streams.topic07_peek;

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
 * Debug stream pipeline.
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: questions.md - original question 50
 */
public class Q050_DebugStreamPipeline {
    public static void main(String[] args) {
        System.out.println("Question: Debug stream pipeline.");
        System.out.println("Source: questions.md - original question 50");
        System.out.println("Input: deterministic sample data");

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        Object result = switch (50) {
            case 50 -> words().stream().peek(v -> System.out.println("source=" + v)).filter(v -> v.startsWith("A"))
                                .peek(v -> System.out.println("filtered=" + v)).map(String::toUpperCase).toList();
            default -> throw new IllegalStateException("Unexpected question");
        };
        System.out.println("Output: " + result);
    }
}
