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

    public static List<Integer> numbers() {
        return List.of(3, 8, 2, 8, 15, 21, 4, 100, 101, 11, 6);
    }

    public static List<String> words() {
        return List.of("Apple", "box", "level", "Apex", "stream", "box", "x", "radar");
    }

    public static List<Employee> employees() {
        return List.of(
                new Employee(1, "Asha", 29, "IT", 82_000),
                new Employee(2, "Ben", 41, "HR", 54_000),
                new Employee(3, "Chen", 35, "IT", 96_000),
                new Employee(4, "Diya", 24, "Sales", 47_000),
                new Employee(5, "Evan", 35, "HR", 54_000),
                new Employee(6, "Fatima", 31, "IT", 71_000));
    }

    public static List<Product> products() {
        return List.of(new Product(1, "Keyboard", "Tech", 70),
                new Product(2, "Book", "Books", 25), new Product(3, "Mouse", "Tech", 35));
    }
}
