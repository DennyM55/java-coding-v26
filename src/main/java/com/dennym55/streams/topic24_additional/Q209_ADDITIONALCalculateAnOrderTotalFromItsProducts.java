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
 * ADDITIONAL: Calculate an Order total from its Products
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {@code Output: ...}.
 *
 * <p>Source: PRACTICE_INDEX.md - additional question 6
 */
public class Q209_ADDITIONALCalculateAnOrderTotalFromItsProducts {
    public static void main(String[] args) {
        System.out.println("Question: ADDITIONAL: Calculate an Order total from its Products");
        System.out.println("Source: PRACTICE_INDEX.md - additional question 6");
        System.out.println("Input: deterministic sample data");

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        Object result = switch (209) {
            case 209 -> new PracticeData.Order(1, "Asha", products()).products().stream().mapToDouble(PracticeData.Product::price).sum();
            default -> throw new IllegalStateException("Unexpected question");
        };
        System.out.println("Output: " + result);
    }
}
