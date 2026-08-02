package com.dennym55.streams.support;

import com.dennym55.streams.support.PracticeData.Employee;
import com.dennym55.streams.support.PracticeData.Person;
import com.dennym55.streams.support.PracticeData.Student;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.*;
import java.util.stream.*;

import static com.dennym55.streams.support.PracticeData.*;

/** Executes the documented examples while keeping fixtures and bulky helpers out of the question launchers. */
public final class PracticeRunner {
    private PracticeRunner() {}

    public static void run(int id, String question, String source) {
        System.out.println("Question: " + question);
        System.out.println("Source: " + source);
        Object result = id <= 10 ? basics(id) : id <= 20 ? filters(id) : id <= 30 ? maps(id)
                : id <= 40 ? sorting(id) : id <= 49 ? distinctLimit(id) : id <= 60 ? peekMatchFind(id)
                : id <= 76 ? aggregate(id) : id <= 90 ? collectors(id) : id <= 101 ? grouping(id)
                : id <= 114 ? flatMapOptional(id) : id <= 137 ? primitiveReferencesFunctions(id)
                : id <= 142 ? parallel(id) : id <= 180 ? interviews(id) : additionalOrTheory(id);
        System.out.println("Output: " + result);
    }

    private static Object basics(int id) {
        List<Integer> input = numbers();
        System.out.println("Input: " + input);
        return switch (id) {
            case 1 -> input.stream().filter(n -> n % 2 != 0).toList();
            case 2 -> input.stream().filter(n -> n % 2 == 0).toList();
            case 3 -> input.stream().count();
            case 4 -> input.stream().findFirst();
            case 5 -> input.stream().findAny();
            case 6 -> input.stream().findAny().isEmpty();
            case 7 -> input.stream().toList();
            case 8 -> Stream.of("A", "B", "C").toList();
            case 9 -> Map.of("of", Stream.of(1, 2).toList(), "collection", input.stream().limit(2).toList(),
                    "array", Arrays.stream(new int[]{1, 2}).boxed().toList(),
                    "iterate", Stream.iterate(0, n -> n + 2).limit(3).toList(),
                    "generate", Stream.generate(() -> 7).limit(3).toList());
            case 10 -> "A Collection stores reusable data; a Stream is a lazy, single-use processing pipeline.";
            default -> throw unknown(id);
        };
    }

    private static Object filters(int id) {
        System.out.println("Input words: " + words());
        System.out.println("Input numbers: " + numbers());
        return switch (id) {
            case 11 -> words().stream().filter(s -> s.startsWith("A")).toList();
            case 12 -> words().stream().filter(s -> s.endsWith("x")).toList();
            case 13 -> numbers().stream().filter(n -> n > 100).toList();
            case 14 -> numbers().stream().filter(n -> n >= 8 && n <= 21).toList();
            case 15 -> numbers().stream().filter(PracticeRunner::isPrime).toList();
            case 16 -> words().stream().filter(PracticeRunner::isPalindrome).toList();
            case 17 -> Arrays.asList("A", null, "B", null).stream().filter(Objects::nonNull).toList();
            case 18 -> List.of("", "Java", "", "Streams").stream().filter(s -> !s.isEmpty()).toList();
            case 19 -> employees().stream().filter(e -> e.salary() > 50_000).toList();
            case 20 -> employees().stream().filter(e -> e.department().equals("IT")).toList();
            default -> throw unknown(id);
        };
    }

    private static Object maps(int id) {
        System.out.println("Input: " + words());
        return switch (id) {
            case 21 -> numbers().stream().map(n -> n * n).toList();
            case 22 -> numbers().stream().map(n -> n * n * n).toList();
            case 23 -> words().stream().map(String::toUpperCase).toList();
            case 24 -> words().stream().map(String::toLowerCase).toList();
            case 25 -> words().stream().map(String::length).toList();
            case 26 -> employees().stream().map(Employee::name).toList();
            case 27 -> employees().stream().map(Employee::salary).toList();
            case 28 -> numbers().stream().map(String::valueOf).toList();
            case 29 -> List.of(" Java ", " Streams ").stream().map(String::trim).toList();
            case 30 -> words().stream().map(s -> "prefix_" + s).toList();
            default -> throw unknown(id);
        };
    }

    private static Object sorting(int id) {
        System.out.println("Input: " + employees());
        return switch (id) {
            case 31 -> numbers().stream().sorted().toList();
            case 32 -> numbers().stream().sorted(Comparator.reverseOrder()).toList();
            case 33 -> words().stream().sorted().toList();
            case 34 -> words().stream().sorted(Comparator.comparingInt(String::length)).toList();
            case 35 -> employees().stream().sorted(Comparator.comparingDouble(Employee::salary)).toList();
            case 36 -> employees().stream().sorted(Comparator.comparingInt(Employee::age)).toList();
            case 37 -> employees().stream().sorted(Comparator.comparing(Employee::department).thenComparingDouble(Employee::salary)).toList();
            case 38 -> words().stream().sorted(Comparator.reverseOrder()).toList();
            case 39 -> employees().stream().sorted((a, b) -> Double.compare(b.salary(), a.salary())).toList();
            case 40 -> List.of("A1", "B1", "A2", "B2").stream().sorted(Comparator.comparing(s -> s.substring(0, 1))).toList();
            default -> throw unknown(id);
        };
    }

    private static Object distinctLimit(int id) {
        return switch (id) {
            case 41 -> numbers().stream().distinct().toList();
            case 42 -> words().stream().distinct().toList();
            case 43 -> employees().stream().map(Employee::department).distinct().toList();
            case 44 -> Stream.of(employees().getFirst(), employees().getFirst(), employees().get(1)).distinct().toList();
            case 45 -> numbers().stream().limit(5).toList();
            case 46 -> numbers().stream().skip(5).toList();
            case 47 -> numbers().stream().skip(2L * 3).limit(3).toList();
            case 48 -> employees().stream().map(Employee::salary).sorted(Comparator.reverseOrder()).limit(10).toList();
            case 49 -> employees().stream().map(Employee::salary).distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst();
            default -> throw unknown(id);
        };
    }

    private static Object peekMatchFind(int id) {
        return switch (id) {
            case 50 -> words().stream().peek(v -> System.out.println("source=" + v)).filter(v -> v.startsWith("A"))
                    .peek(v -> System.out.println("filtered=" + v)).map(String::toUpperCase).toList();
            case 51 -> numbers().stream().filter(n -> n % 2 == 0).peek(n -> System.out.println("even=" + n)).map(n -> n * n).toList();
            case 52 -> numbers().stream().allMatch(n -> n > 0);
            case 53 -> numbers().stream().anyMatch(n -> n > 100);
            case 54 -> numbers().stream().noneMatch(n -> n < 0);
            case 55 -> employees().stream().allMatch(e -> e.salary() > 10_000);
            case 56 -> employees().stream().anyMatch(e -> e.department().equals("HR"));
            case 57 -> employees().stream().findFirst();
            case 58 -> numbers().stream().filter(n -> n % 2 != 0).findFirst();
            case 59 -> numbers().stream().filter(PracticeRunner::isPrime).findAny();
            case 60 -> "findFirst preserves encounter order; findAny may select any element, enabling parallel optimization.";
            default -> throw unknown(id);
        };
    }

    private static Object aggregate(int id) {
        return switch (id) {
            case 61 -> numbers().stream().max(Integer::compareTo);
            case 62 -> numbers().stream().min(Integer::compareTo);
            case 63 -> words().stream().max(Comparator.comparingInt(String::length));
            case 64 -> words().stream().min(Comparator.comparingInt(String::length));
            case 65 -> employees().stream().max(Comparator.comparingDouble(Employee::salary));
            case 66 -> employees().stream().min(Comparator.comparingInt(Employee::age));
            case 67 -> employees().stream().count();
            case 68 -> numbers().stream().filter(n -> n % 2 == 0).count();
            case 69 -> words().stream().filter(s -> s.startsWith("A")).count();
            case 70 -> numbers().stream().reduce(0, Integer::sum);
            case 71 -> List.of(1, 2, 3, 4).stream().reduce(1, (a, b) -> a * b);
            case 72 -> numbers().stream().reduce(Integer::max);
            case 73 -> numbers().stream().reduce(Integer::min);
            case 74 -> words().stream().reduce((a, b) -> a + "," + b);
            case 75 -> IntStream.rangeClosed(1, 5).reduce(1, (a, b) -> a * b);
            case 76 -> employees().stream().map(Employee::salary).reduce(0.0, Double::sum);
            default -> throw unknown(id);
        };
    }

    private static Object collectors(int id) {
        Stream<String> stream = words().stream();
        return switch (id) {
            case 77 -> stream.collect(Collectors.toList());
            case 78 -> stream.collect(Collectors.toSet());
            case 79 -> employees().stream().collect(Collectors.toMap(Employee::id, Employee::name));
            case 80 -> stream.collect(Collectors.joining(", "));
            case 81 -> stream.collect(Collectors.counting());
            case 82 -> employees().stream().collect(Collectors.averagingInt(Employee::age));
            case 83 -> employees().stream().collect(Collectors.averagingDouble(Employee::salary));
            case 84 -> employees().stream().collect(Collectors.summingInt(Employee::age));
            case 85 -> employees().stream().collect(Collectors.summarizingInt(Employee::age));
            case 86 -> employees().stream().collect(Collectors.groupingBy(Employee::department));
            case 87 -> employees().stream().collect(Collectors.partitioningBy(e -> e.salary() > 50_000));
            case 88 -> employees().stream().collect(Collectors.mapping(Employee::department, Collectors.toSet()));
            case 89 -> stream.collect(Collectors.collectingAndThen(Collectors.toList(), List::copyOf));
            case 90 -> stream.collect(Collectors.toUnmodifiableList());
            default -> throw unknown(id);
        };
    }

    private static Object grouping(int id) {
        List<Student> students = List.of(new Student("Ana", 90), new Student("Bo", 70), new Student("Cy", 90));
        List<Person> people = List.of(new Person("Kid", 12), new Person("Adult", 22), new Person("Senior", 65));
        return switch (id) {
            case 91 -> employees().stream().collect(Collectors.groupingBy(Employee::department));
            case 92 -> students.stream().collect(Collectors.groupingBy(Student::grade));
            case 93 -> products().stream().collect(Collectors.groupingBy(PracticeData.Product::category));
            case 94 -> employees().stream().collect(Collectors.groupingBy(Employee::age));
            case 95 -> employees().stream().collect(Collectors.groupingBy(Employee::department, Collectors.counting()));
            case 96 -> employees().stream().collect(Collectors.groupingBy(Employee::department,
                    Collectors.maxBy(Comparator.comparingDouble(Employee::salary))));
            case 97 -> employees().stream().collect(Collectors.groupingBy(Employee::department,
                    Collectors.averagingDouble(Employee::salary)));
            case 98 -> numbers().stream().collect(Collectors.partitioningBy(n -> n % 2 == 0));
            case 99 -> students.stream().collect(Collectors.partitioningBy(s -> s.score() >= 60));
            case 100 -> people.stream().collect(Collectors.partitioningBy(p -> p.age() >= 18));
            case 101 -> employees().stream().collect(Collectors.partitioningBy(e -> e.salary() > 50_000));
            default -> throw unknown(id);
        };
    }

    private static Object flatMapOptional(int id) {
        Optional<String> value = Optional.of("Apple");
        return switch (id) {
            case 102 -> List.of(List.of(1, 2), List.of(3, 4)).stream().flatMap(List::stream).toList();
            case 103 -> List.of(List.of("Java", "SQL"), List.of("Java", "Docker")).stream().flatMap(List::stream).distinct().toList();
            case 104 -> List.of(Set.of("A", "B"), Set.of("C")).stream().flatMap(Collection::stream).toList();
            case 105 -> List.of(Optional.of("A"), Optional.<String>empty(), Optional.of("B")).stream().flatMap(Optional::stream).toList();
            case 106 -> Optional.ofNullable((String) null).orElse("default");
            case 107 -> value.orElse("default");
            case 108 -> Optional.<String>empty().orElseGet(() -> "computed lazily");
            case 109 -> capture(() -> Optional.<String>empty().orElseThrow(() -> new IllegalStateException("Value missing")));
            case 110 -> { value.ifPresent(v -> System.out.println("Present: " + v)); yield value; }
            case 111 -> value.map(String::length);
            case 112 -> value.flatMap(v -> v.isBlank() ? Optional.empty() : Optional.of(v.toUpperCase()));
            case 113 -> value.filter(v -> v.startsWith("A"));
            case 114 -> value.stream().toList();
            default -> throw unknown(id);
        };
    }

    private static Object primitiveReferencesFunctions(int id) {
        return switch (id) {
            case 115 -> IntStream.of(1, 2, 3).sum();
            case 116 -> LongStream.of(1L, 2L, 3L).sum();
            case 117 -> DoubleStream.of(1.5, 2.5).average();
            case 118 -> IntStream.range(0, 5).boxed().toList();
            case 119 -> IntStream.rangeClosed(0, 5).boxed().toList();
            case 120 -> Arrays.toString(words().stream().mapToInt(String::length).toArray());
            case 121 -> IntStream.of(1, 2, 3).boxed().toList();
            case 122 -> IntStream.of(1, 2, 3, 4, 5).summaryStatistics();
            case 123 -> { words().forEach(System.out::println); yield "printed with method reference"; }
            case 124 -> words().stream().map(String::length).toList();
            case 125 -> List.of("10", "20").stream().map(Integer::parseInt).toList();
            case 126 -> employees().stream().map(Employee::salary).toList();
            case 127 -> List.of(7, 8).stream().map(i -> new Employee(i, "Employee" + i, 30, "IT", 50_000)).toList();
            case 128 -> ((Predicate<String>) s -> s.startsWith("A")).test("Apple");
            case 129 -> ((BiPredicate<String, Integer>) (s, n) -> s.length() == n).test("Java", 4);
            case 130 -> ((Function<String, Integer>) String::length).apply("Stream");
            case 131 -> ((BiFunction<String, String, String>) String::concat).apply("Java", " Streams");
            case 132 -> { Consumer<String> c = System.out::println; c.accept("Consumed"); yield "Consumer returned no value"; }
            case 133 -> { BiConsumer<String, Integer> c = (s, n) -> System.out.println(s + n); c.accept("Value=", 5); yield "BiConsumer completed"; }
            case 134 -> ((Supplier<Double>) Math::random).get();
            case 135 -> ((UnaryOperator<String>) String::toUpperCase).apply("java");
            case 136 -> ((BinaryOperator<Integer>) Integer::sum).apply(2, 3);
            case 137 -> { TextOperation reverse = s -> new StringBuilder(s).reverse().toString(); yield reverse.apply("stream"); }
            default -> throw unknown(id);
        };
    }

    private static Object parallel(int id) {
        return switch (id) {
            case 138 -> words().parallelStream().map(String::toUpperCase).toList();
            case 139 -> "stream uses the caller thread; parallelStream partitions work over the common ForkJoinPool.";
            case 140 -> "Avoid parallel streams for small inputs, blocking I/O, ordered work, or shared mutable state.";
            case 141 -> { AtomicInteger safe = new AtomicInteger(); IntStream.range(0, 1_000).parallel().forEach(i -> safe.incrementAndGet()); yield safe.get(); }
            case 142 -> benchmark();
            default -> throw unknown(id);
        };
    }

    private static Object interviews(int id) {
        List<Integer> n = numbers();
        List<String> w = words();
        String text = "java streams make java concise streams";
        return switch (id) {
            case 143 -> employees().stream().map(Employee::salary).distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst();
            case 144 -> employees().stream().map(Employee::salary).distinct().sorted().skip(1).findFirst();
            case 145 -> n.stream().filter(x -> Collections.frequency(n, x) > 1).collect(Collectors.toSet());
            case 146 -> "swiss".chars().mapToObj(c -> (char)c).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                    .entrySet().stream().filter(e -> e.getValue() == 1).map(Map.Entry::getKey).findFirst();
            case 147 -> "banana".chars().mapToObj(c -> (char)c).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
            case 148 -> Arrays.stream(text.split("\\s+")).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()));
            case 149 -> w.stream().max(Comparator.comparingInt(String::length));
            case 150 -> w.stream().min(Comparator.comparingInt(String::length));
            case 151 -> w.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream().max(Map.Entry.comparingByValue());
            case 152 -> employees().stream().map(Employee::salary).sorted(Comparator.reverseOrder()).limit(3).toList();
            case 153 -> employees().stream().collect(Collectors.groupingBy(Employee::department));
            case 154 -> employees().stream().collect(Collectors.groupingBy(Employee::department,
                    Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparingDouble(Employee::salary)), Optional::orElseThrow)));
            case 155 -> Stream.concat(employees().stream(), Stream.of(employees().getFirst())).distinct().toList();
            case 156 -> { int[] a = {1, 2, 4, 5}; int size = a.length + 1; yield size * (size + 1) / 2 - Arrays.stream(a).sum(); }
            case 157 -> Stream.concat(List.of("A", "B").stream(), List.of("C", "D").stream()).toList();
            case 158, 160 -> List.of("A", "B", "C").stream().filter(Set.of("B", "C", "D")::contains).distinct().toList();
            case 159 -> Stream.concat(List.of("A", "B").stream(), List.of("B", "C").stream()).distinct().toList();
            case 161 -> List.of("A", "B", "C").stream().filter(x -> !Set.of("B", "D").contains(x)).toList();
            case 162 -> employees().stream().sorted(Comparator.comparing(Employee::department).thenComparingDouble(Employee::salary).thenComparing(Employee::name)).toList();
            case 163 -> employees().stream().collect(Collectors.groupingBy(Employee::department, Collectors.averagingDouble(Employee::salary)));
            case 164 -> employees().stream().collect(Collectors.groupingBy(Employee::department, Collectors.summingDouble(Employee::salary)));
            case 165 -> Arrays.stream(text.split("\\s+")).collect(Collectors.groupingBy(Function.identity(), Collectors.counting())).entrySet().stream()
                    .filter(e -> e.getValue() > 1).map(Map.Entry::getKey).collect(Collectors.toSet());
            case 166 -> Arrays.stream(text.split("\\s+")).map(x -> new StringBuilder(x).reverse().toString()).collect(Collectors.joining(" "));
            case 167 -> Arrays.stream(text.split("\\s+")).collect(Collectors.collectingAndThen(Collectors.toList(), list -> list.reversed().stream().collect(Collectors.joining(" "))));
            case 168 -> text.chars().filter(c -> "aeiouAEIOU".indexOf(c) >= 0).count();
            case 169 -> text.chars().filter(Character::isLetter).filter(c -> "aeiouAEIOU".indexOf(c) < 0).count();
            case 170 -> w.stream().filter(PracticeRunner::isPalindrome).toList();
            case 171 -> List.of("eat", "tea", "tan", "ate", "nat").stream().collect(Collectors.groupingBy(PracticeRunner::sortedLetters));
            case 172 -> employees().stream().sorted(Comparator.comparingDouble(Employee::salary).reversed()).limit(3).toList();
            case 173 -> employees().stream().skip(1L * 2).limit(2).toList();
            case 174 -> flattenHierarchy(sampleHierarchy()).toList();
            case 175 -> employees().stream().collect(Collectors.toMap(Employee::id, Function.identity()));
            case 176 -> Stream.concat(employees().stream(), Stream.of(employees().getFirst())).collect(Collectors.toMap(Employee::email, Function.identity(), (first, ignored) -> first));
            case 177 -> n.stream().distinct().sorted(Comparator.reverseOrder()).skip(2).findFirst();
            case 178 -> n.stream().distinct().sorted().skip(2).findFirst();
            case 179 -> w.stream().collect(Collector.of(ArrayList::new, List::add, (left, right) -> { left.addAll(right); return left; }));
            case 180 -> n.stream().filter(x -> x % 2 == 0).map(x -> x * x).distinct().sorted().collect(Collectors.toList());
            default -> throw unknown(id);
        };
    }

    private static Object additionalOrTheory(int id) {
        return switch (id) {
            case 204 -> List.of("a", "bb", "ccc").stream().map(String::length).toList();
            case 205 -> { AtomicInteger calls = new AtomicInteger(); var firstOnly = Stream.of(1, 2, 3).map(x -> { calls.incrementAndGet(); return x * 2; }).limit(1).toList(); yield Map.of("result", firstOnly, "mapped elements", calls.get()); }
            case 206 -> IntStream.rangeClosed(1, 10).boxed().takeWhile(x -> x < 5).toList();
            case 207 -> IntStream.rangeClosed(1, 10).boxed().dropWhile(x -> x < 5).toList();
            case 208 -> Stream.concat(products().stream(), Stream.of(products().getFirst())).collect(Collectors.toMap(PracticeData.Product::category, PracticeData.Product::name, (a, b) -> a + ", " + b));
            case 209 -> new PracticeData.Order(1, "Asha", products()).products().stream().mapToDouble(PracticeData.Product::price).sum();
            case 210 -> IntStream.range(0, 10_000).boxed().mapToInt(Integer::intValue).sum();
            case 211 -> capture(() -> { Stream<Integer> stream = Stream.of(1, 2); stream.count(); return stream.count(); });
            default -> theoryAnswer(id);
        };
    }

    private static String theoryAnswer(int id) {
        String[] answers = {
                "A Stream is a sequence supporting aggregate operations; it does not store elements.",
                "Collections store reusable data; streams are lazy, consumable pipelines.",
                "Intermediate operations return a stream and are lazy; terminal operations consume it.",
                "Lazy evaluation delays work until a terminal operation requests elements.",
                "Short-circuiting can finish without visiting every element.",
                "Stateless operations handle elements independently; stateful operations retain cross-element state.",
                "A terminal operation consumes a stream, so a new stream is required for another traversal.",
                "A pipeline is a source, zero or more intermediate operations, and a terminal operation.",
                "External iteration is controlled by client code; internal iteration is controlled by the stream.",
                "Predicate tests, Function transforms, Consumer observes, and Supplier produces.",
                "A method reference is concise syntax for a lambda that only invokes an existing method.",
                "Use Optional mainly as a return type and prefer map/flatMap/orElseGet over null or eager defaults.",
                "map produces one mapped value; flatMap maps and flattens nested streams.",
                "forEach is terminal; peek is an intermediate diagnostic operation.",
                "sorted performs sorting; Comparator supplies the ordering rule.",
                "reduce combines immutable values; collect performs mutable reduction into containers.",
                "Collectors.toList makes no immutability guarantee; Stream.toList returns an unmodifiable list.",
                "findFirst respects encounter order; findAny permits any element.",
                "groupingBy creates arbitrary key groups; partitioningBy always creates boolean partitions.",
                "Parallel streams split with a Spliterator and execute tasks in the common ForkJoinPool.",
                "Spliterator traverses and can partition a source for parallel processing.",
                "Streams can hurt small hot loops, boxing-heavy code, stateful pipelines, and poorly chosen parallel work.",
                "Common traps include stream reuse, side effects, unsafe parallel mutation, and forgotten resource closing."
        };
        int index = id - 181;
        if (index < 0 || index >= answers.length) throw unknown(id);
        return answers[index];
    }

    public static boolean isPrime(int n) { return n > 1 && IntStream.rangeClosed(2, (int)Math.sqrt(n)).noneMatch(i -> n % i == 0); }
    public static boolean isPalindrome(String s) { return s.equalsIgnoreCase(new StringBuilder(s).reverse().toString()); }
    public static String sortedLetters(String s) { return s.chars().sorted().collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append).toString(); }
    private static RuntimeException unknown(int id) { return new IllegalArgumentException("Unknown question " + id); }
    public static Object capture(Supplier<?> action) { try { return action.get(); } catch (RuntimeException e) { return e.getClass().getSimpleName() + ": " + e.getMessage(); } }
    public static Map<String, Long> benchmark() {
        List<Integer> input = IntStream.range(0, 100_000).boxed().toList();
        long start = System.nanoTime(); input.stream().mapToLong(x -> (long)x * x).sum(); long sequential = System.nanoTime() - start;
        start = System.nanoTime(); input.parallelStream().mapToLong(x -> (long)x * x).sum(); long parallel = System.nanoTime() - start;
        return Map.of("sequentialNanos", sequential, "parallelNanos", parallel);
    }
    public static Employee sampleHierarchy() {
        Employee leaf = new Employee(12, "Leaf", 22, "IT", 40_000);
        Employee manager = new Employee(11, "Manager", 35, "IT", 80_000, "manager@example.com", List.of("Java"), List.of(leaf));
        return new Employee(10, "Director", 45, "IT", 120_000, "director@example.com", List.of("Leadership"), List.of(manager));
    }
    public static Stream<Employee> flattenHierarchy(Employee employee) {
        return Stream.concat(Stream.of(employee), employee.subordinates().stream().flatMap(PracticeRunner::flattenHierarchy));
    }

    @FunctionalInterface public interface TextOperation { String apply(String value); }
}
