package com.dennym55.streams.topic27_advanced_collectors;

import com.dennym55.streams.support.PracticeData.Employee;

import java.util.List;
import java.util.stream.Collectors;

import static com.dennym55.streams.support.PracticeData.employees;

/**
 * Q217: Use Collectors.teeing() to calculate employee count and total salary in one collection,
 * then merge both results into an immutable summary.
 *
 * <p>Input: sample employees.
 * Expected output: count 6, total 404,000 and average 67,333.33 (approximately).
 */
public class Q217_TeeingCollector {

    public record SalarySummary(long count, double total, double average) {}

    public static SalarySummary summarize(List<Employee> input) {
        return input.stream().collect(Collectors.teeing(
                Collectors.counting(),
                Collectors.summingDouble(Employee::salary),
                (count, total) -> new SalarySummary(count, total, count == 0 ? 0.0 : total / count)));
    }

    public static void main(String[] args) {
        List<Employee> input = employees();
        System.out.println("Input: " + input);
        System.out.println("Output: " + summarize(input));
        System.out.println("Edge case: an empty stream produces count=0, total=0 and average=0.");
    }
}
