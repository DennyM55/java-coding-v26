package com.dennym55.streams.topic27_advanced_collectors;

import com.dennym55.streams.support.PracticeData.Employee;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static com.dennym55.streams.support.PracticeData.employees;

/**
 * Q218: Perform nested grouping by department and age band, then use a downstream mapping
 * collector to retain sorted employee names.
 *
 * <p>Input: sample employees.
 * Expected output: department -> age band -> sorted set of employee names.
 */
public class Q218_NestedGroupingAndDownstreamCollectors {

    public static String ageBand(Employee employee) {
        return employee.age() < 30 ? "UNDER_30" : employee.age() < 40 ? "30_TO_39" : "40_PLUS";
    }

    public static Map<String, Map<String, Set<String>>> group(List<Employee> input) {
        return input.stream().collect(Collectors.groupingBy(
                Employee::department,
                Collectors.groupingBy(
                        Q218_NestedGroupingAndDownstreamCollectors::ageBand,
                        Collectors.mapping(Employee::name, Collectors.toCollection(TreeSet::new)))));
    }

    public static void main(String[] args) {
        List<Employee> input = employees();
        System.out.println("Input: " + input);
        System.out.println("Output: " + group(input));
    }
}
