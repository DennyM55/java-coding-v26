package com.dennym55.streams.topic23_interview_theory;

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
 * Common Stream mistakes and anti-patterns. ([GeeksforGeeks][1])
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: questions.md - Interview Theory Question 23
 */
public class Q203_CommonStreamMistakesAndAntiPatternsGeeksforGeeks1 {
    public static void main(String[] args) {
        System.out.println("Question: Common Stream mistakes and anti-patterns. ([GeeksforGeeks][1])");
        System.out.println("Source: questions.md - Interview Theory Question 23");
        System.out.println("Input: deterministic sample data");

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        String result = "Common traps include stream reuse, side effects, unsafe parallel mutation, and forgotten resource closing.";
        System.out.println("Output: " + result);
    }
}
