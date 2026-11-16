package com.dennym55.streams.topic27_advanced_collectors;

import com.dennym55.streams.support.PracticeData.Employee;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.dennym55.streams.support.PracticeData.employees;

/**
 * Q215: Use Collectors.filtering() downstream of groupingBy() while retaining groups that have
 * no matching elements.
 *
 * <p>Input: sample employees grouped by department, salary threshold 90,000.
 * Expected output: IT contains Chen; HR and Sales remain present with empty lists.
 */
public class Q215_DownstreamFilteringCollector {

    public static Map<String, List<Employee>> highEarnersByDepartment(
            List<Employee> input, double minimumSalary) {
        return input.stream().collect(Collectors.groupingBy(
                Employee::department,
                Collectors.filtering(employee -> employee.salary() >= minimumSalary, Collectors.toList())));
    }

    public static void main(String[] args) {
        List<Employee> input = employees();
        Map<String, List<Employee>> output = highEarnersByDepartment(input, 90_000);
        System.out.println("Input: " + input);
        System.out.println("Output: " + output);
        System.out.println("Pitfall: filtering before grouping would remove HR and Sales keys entirely.");
    }
}
