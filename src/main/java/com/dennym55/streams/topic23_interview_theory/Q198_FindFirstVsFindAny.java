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
 * `findFirst()` vs `findAny()`
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: questions.md - Interview Theory Question 18
 */
public class Q198_FindFirstVsFindAny {
    public static void main(String[] args) {
        System.out.println("Question: `findFirst()` vs `findAny()`");
        System.out.println("Source: questions.md - Interview Theory Question 18");
        System.out.println("Input: deterministic sample data");

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        String result = "findFirst respects encounter order; findAny permits any element.";
        System.out.println("Output: " + result);
    }
}
