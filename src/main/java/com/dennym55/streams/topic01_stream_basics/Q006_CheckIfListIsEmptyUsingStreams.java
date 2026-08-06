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
 * Check if list is empty using streams.
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: questions.md - original question 6
 */
public class Q006_CheckIfListIsEmptyUsingStreams {
    public static void main(String[] args) {
        System.out.println("Question: Check if list is empty using streams.");
        System.out.println("Source: questions.md - original question 6");
        List<Integer> input = numbers();
        System.out.println("Input: " + input);

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        Object result = switch (6) {
            case 6 -> input.stream().findAny().isEmpty();
            default -> throw new IllegalStateException("Unexpected question");
        };
        System.out.println("Output: " + result);
    }
}
