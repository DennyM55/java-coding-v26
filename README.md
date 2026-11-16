# Java Streams Solved Practice Repository

This Maven/Java 26 project turns the repository's original Streams roadmap into independently runnable solved examples.

The repository did not originally contain a root `README.md`. Its source material remains preserved verbatim in:

- [`questions.md`](src/main/java/streams/questions.md) — the primary question order
- [`answers-anki-mode.md`](src/main/java/streams/answers-anki-mode.md) — the original answer notes

No original question was removed or merged. The 180 numbered questions and 23 interview-theory questions each have a runnable class. Intentional duplicates remain separate. Eight foundational `ADDITIONAL` examples are followed by fifteen advanced examples, Q212-Q226.

Use the complete clickable [`PRACTICE_INDEX.md`](PRACTICE_INDEX.md) to navigate all 226 classes in recommended order.

## Run

```powershell
mvn clean test
mvn -q -DskipTests package
```

In IntelliJ IDEA, open any indexed class and run its `public static void main(String[] args)` method. Start with [`Q001_FilterAllOddNumbers.java`](src/main/java/com/dennym55/streams/topic01_stream_basics/Q001_FilterAllOddNumbers.java).

## Design

Each question has a dedicated sequence-prefixed class with the original wording and source reference in JavaDoc. Its complete solution expression is visible directly inside `main`. Only deterministic fixtures, domain records, and genuinely reusable helpers live in `com.dennym55.streams.support`.

## Advanced learning order

After Q211, practice the advanced material in this order:

1. Q212-Q213: closures, higher-order functions, purity, immutability and side effects.
2. Q214: `ofNullable`, `concat`, builders and bounded `iterate`.
3. Q215-Q218: downstream collector composition and nested grouping.
4. Revisit improved Q179: a complete custom collector lifecycle and parallel combiner.
5. Q219-Q220: custom Spliterators, `tryAdvance`, `trySplit` and characteristics.
6. Revisit improved Q141, then Q221-Q222: races, ordering, reductions, custom pools and JMH.
7. Q223-Q225: Java 24+ fixed/sliding windows, fold, scan and custom Gatherers.
8. Q226: consolidated advanced interview traps and safe alternatives.

Q222 is a real JMH benchmark. Build the project first, then run its `main()` from IntelliJ; benchmark results vary by hardware and should not be treated as unit-test assertions.

The advanced APIs follow the Java 26 [`Stream`](https://docs.oracle.com/en/java/javase/26/docs/api/java.base/java/util/stream/Stream.html), [`Collectors`](https://docs.oracle.com/en/java/javase/26/docs/api/java.base/java/util/stream/Collectors.html), and [`Gatherers`](https://docs.oracle.com/en/java/javase/26/docs/api/java.base/java/util/stream/Gatherers.html) contracts. Benchmark configuration follows the [OpenJDK JMH project](https://github.com/openjdk/jmh).
