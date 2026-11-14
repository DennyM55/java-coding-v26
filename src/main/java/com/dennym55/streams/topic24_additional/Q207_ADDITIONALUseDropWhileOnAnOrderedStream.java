package com.dennym55.streams.topic24_additional;

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
 * ADDITIONAL: Use dropWhile() on an ordered stream
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: PRACTICE_INDEX.md - additional question 4
 */
public class Q207_ADDITIONALUseDropWhileOnAnOrderedStream {
    public static void main(String[] args) {
        System.out.println("Question: ADDITIONAL: Use dropWhile() on an ordered stream");
        System.out.println("Source: PRACTICE_INDEX.md - additional question 4");
        System.out.println("Input: deterministic sample data");

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        Object result = switch (207) {
            case 207 -> IntStream.rangeClosed(1, 10).boxed().dropWhile(x -> x < 5).toList();
            default -> throw new IllegalStateException("Unexpected question");
        };
        System.out.println("Output: " + result);
    }
}
