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
 * Convert Stream to List.
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: questions.md - original question 8
 */
public class Q008_ConvertStreamToList {
    public static void main(String[] args) {
        System.out.println("Question: Convert Stream to List.");
        System.out.println("Source: questions.md - original question 8");
        List<Integer> input = numbers();
        System.out.println("Input: " + input);

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        Object result = switch (8) {
            case 8 -> Stream.of("A", "B", "C").toList();
            default -> throw new IllegalStateException("Unexpected question");
        };
        System.out.println("Output: " + result);
    }
}
