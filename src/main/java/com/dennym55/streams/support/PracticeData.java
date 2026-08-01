package com.dennym55.streams.support;

import java.util.List;

/** Shared immutable fixtures used by the independently runnable examples. */
public final class PracticeData {
    private PracticeData() {}

    public record Employee(int id, String name, int age, String department, double salary,
                           String email, List<String> skills, List<Employee> subordinates) {
        public Employee(int id, String name, int age, String department, double salary) {
            this(id, name, age, department, salary, name.toLowerCase() + "@example.com", List.of(), List.of());
        }
    }

    public record Student(String name, int score) {
        public String grade() { return score >= 80 ? "A" : score >= 60 ? "B" : "C"; }
    }

    public record Product(int id, String name, String category, double price) {}
    public record Order(int id, String customer, List<Product> products) {}
    public record Person(String name, int age) {}
}