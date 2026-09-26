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
 * orElseGet()
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: questions.md - original question 108
 */
public class Q108_OrElseGet {
    public static void main(String[] args) {
        System.out.println("Question: orElseGet()");
        System.out.println("Source: questions.md - original question 108");
        Optional<String> value = Optional.of("Apple");

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        Object result = switch (108) {
            case 108 -> Optional.<String>empty().orElseGet(() -> "computed lazily");
            default -> throw new IllegalStateException("Unexpected question");
        };
        System.out.println("Output: " + result);
    }
}
