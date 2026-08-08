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
 * Difference between Collection and Stream. ([GeeksforGeeks][1])
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: questions.md - original question 10
 */
public class Q010_DifferenceBetweenCollectionAndStreamGeeksforGeeks1 {
    public static void main(String[] args) {
        System.out.println("Question: Difference between Collection and Stream. ([GeeksforGeeks][1])");
        System.out.println("Source: questions.md - original question 10");
        List<Integer> input = numbers();
        System.out.println("Input: " + input);

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        Object result = switch (10) {
            case 10 -> "A Collection stores reusable data; a Stream is a lazy, single-use processing pipeline.";
            default -> throw new IllegalStateException("Unexpected question");
        };
        System.out.println("Output: " + result);
    }
}
