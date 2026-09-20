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
 * Average salary per department.
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: questions.md - original question 97
 */
public class Q097_AverageSalaryPerDepartment {
    public static void main(String[] args) {
        System.out.println("Question: Average salary per department.");
        System.out.println("Source: questions.md - original question 97");
        List<Student> students = List.of(new Student("Ana", 90), new Student("Bo", 70), new Student("Cy", 90));
        List<Person> people = List.of(new Person("Kid", 12), new Person("Adult", 22), new Person("Senior", 65));

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        Object result = switch (97) {
            case 97 -> employees().stream().collect(Collectors.groupingBy(Employee::department,
                                Collectors.averagingDouble(Employee::salary)));
            default -> throw new IllegalStateException("Unexpected question");
        };
        System.out.println("Output: " + result);
    }
}
