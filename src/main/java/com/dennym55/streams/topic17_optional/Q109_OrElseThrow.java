package com.dennym55.streams.topic17_optional;

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
 * orElseThrow()
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: questions.md - original question 109
 */
public class Q109_OrElseThrow {
    public static void main(String[] args) {
        System.out.println("Question: orElseThrow()");
        System.out.println("Source: questions.md - original question 109");
        Optional<String> value = Optional.of("Apple");

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        Object result = switch (109) {
            case 109 -> capture(() -> Optional.<String>empty().orElseThrow(() -> new IllegalStateException("Value missing")));
            default -> throw new IllegalStateException("Unexpected question");
        };
        System.out.println("Output: " + result);
    }
}
