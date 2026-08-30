package com.dennym55.streams.topic08_matching_operations;

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
 * Check if all salaries > 10000.
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: questions.md - original question 55
 */
public class Q055_CheckIfAllSalaries10000 {
    public static void main(String[] args) {
        System.out.println("Question: Check if all salaries > 10000.");
        System.out.println("Source: questions.md - original question 55");
        System.out.println("Input: deterministic sample data");

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        Object result = switch (55) {
            case 55 -> employees().stream().allMatch(e -> e.salary() > 10_000);
            default -> throw new IllegalStateException("Unexpected question");
        };
        System.out.println("Output: " + result);
    }
}
