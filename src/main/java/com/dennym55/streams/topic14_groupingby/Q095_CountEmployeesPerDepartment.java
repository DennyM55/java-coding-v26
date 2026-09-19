package com.dennym55.streams.topic14_groupingby;

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
 * Count employees per department.
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: questions.md - original question 95
 */
public class Q095_CountEmployeesPerDepartment {
    public static void main(String[] args) {
        System.out.println("Question: Count employees per department.");
        System.out.println("Source: questions.md - original question 95");
        List<Student> students = List.of(new Student("Ana", 90), new Student("Bo", 70), new Student("Cy", 90));
        List<Person> people = List.of(new Person("Kid", 12), new Person("Adult", 22), new Person("Senior", 65));

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        Object result = switch (95) {
            case 95 -> employees().stream().collect(Collectors.groupingBy(Employee::department, Collectors.counting()));
            default -> throw new IllegalStateException("Unexpected question");
        };
        System.out.println("Output: " + result);
    }
}
