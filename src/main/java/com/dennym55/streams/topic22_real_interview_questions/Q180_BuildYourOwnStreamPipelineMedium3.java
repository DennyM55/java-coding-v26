package com.dennym55.streams.topic22_real_interview_questions;

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
 * Build your own stream pipeline. ([Medium][3])
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: questions.md - original question 180
 */
public class Q180_BuildYourOwnStreamPipelineMedium3 {
    public static void main(String[] args) {
        System.out.println("Question: Build your own stream pipeline. ([Medium][3])");
        System.out.println("Source: questions.md - original question 180");
        List<Integer> n = numbers();
        List<String> w = words();
        String text = "java streams make java concise streams";

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        Object result = switch (180) {
            case 180 -> n.stream().filter(x -> x % 2 == 0).map(x -> x * x).distinct().sorted().collect(Collectors.toList());
            default -> throw new IllegalStateException("Unexpected question");
        };
        System.out.println("Output: " + result);
    }
}
