package com.dennym55.streams.topic13_collectors;

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
 * groupingBy()
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: questions.md - original question 86
 */
public class Q086_GroupingBy {
    public static void main(String[] args) {
        System.out.println("Question: groupingBy()");
        System.out.println("Source: questions.md - original question 86");
        Stream<String> stream = words().stream();
        System.out.println("Input: " + words());

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        Object result = switch (86) {
            case 86 -> employees().stream().collect(Collectors.groupingBy(Employee::department));
            default -> throw new IllegalStateException("Unexpected question");
        };
        System.out.println("Output: " + result);
    }
}
