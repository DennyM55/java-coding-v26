package com.dennym55.streams.topic22_real_interview_questions;

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
 * Custom collector.
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: questions.md - original question 179
 */
public class Q179_CustomCollector {
    public static void main(String[] args) {
        System.out.println("Question: Custom collector.");
        System.out.println("Source: questions.md - original question 179");
        List<Integer> n = numbers();
        List<String> w = words();
        String text = "java streams make java concise streams";

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        Object result = switch (179) {
            case 179 -> w.stream().collect(Collector.of(ArrayList::new, List::add, (left, right) -> { left.addAll(right); return left; }));
            default -> throw new IllegalStateException("Unexpected question");
        };
        System.out.println("Output: " + result);
    }
}
