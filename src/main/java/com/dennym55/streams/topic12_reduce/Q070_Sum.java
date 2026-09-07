package com.dennym55.streams.topic12_reduce;

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
 * Sum.
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: questions.md - original question 70
 */
public class Q070_Sum {
    public static void main(String[] args) {
        System.out.println("Question: Sum.");
        System.out.println("Source: questions.md - original question 70");
        System.out.println("Input: deterministic sample data");

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        Object result = switch (70) {
            case 70 -> numbers().stream().reduce(0, Integer::sum);
            default -> throw new IllegalStateException("Unexpected question");
        };
        System.out.println("Output: " + result);
    }
}
