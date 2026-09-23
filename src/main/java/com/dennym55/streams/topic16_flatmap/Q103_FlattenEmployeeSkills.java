package com.dennym55.streams.topic16_flatmap;

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
 * Flatten employee skills.
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: questions.md - original question 103
 */
public class Q103_FlattenEmployeeSkills {
    public static void main(String[] args) {
        System.out.println("Question: Flatten employee skills.");
        System.out.println("Source: questions.md - original question 103");
        System.out.println("Input: deterministic sample data");

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        Object result = switch (103) {
            case 103 -> List.of(List.of("Java", "SQL"), List.of("Java", "Docker")).stream().flatMap(List::stream).distinct().toList();
            default -> throw new IllegalStateException("Unexpected question");
        };
        System.out.println("Output: " + result);
    }
}
