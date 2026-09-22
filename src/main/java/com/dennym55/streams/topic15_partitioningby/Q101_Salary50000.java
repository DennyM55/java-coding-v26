package com.dennym55.streams.topic15_partitioningby;

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
 * Salary > 50000.
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: questions.md - original question 101
 */
public class Q101_Salary50000 {
    public static void main(String[] args) {
        System.out.println("Question: Salary > 50000.");
        System.out.println("Source: questions.md - original question 101");
        List<Student> students = List.of(new Student("Ana", 90), new Student("Bo", 70), new Student("Cy", 90));
        List<Person> people = List.of(new Person("Kid", 12), new Person("Adult", 22), new Person("Senior", 65));

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        Object result = switch (101) {
            case 101 -> employees().stream().collect(Collectors.partitioningBy(e -> e.salary() > 50_000));
            default -> throw new IllegalStateException("Unexpected question");
        };
        System.out.println("Output: " + result);
    }
}
