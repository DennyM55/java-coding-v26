"""Generate the mechanical one-class-per-question launchers and their navigation index."""
from pathlib import Path
import re
import textwrap

ROOT = Path(__file__).resolve().parents[1]
QUESTIONS = ROOT / "src/main/java/streams/questions.md"
JAVA_ROOT = ROOT / "src/main/java/com/dennym55/streams"

text = QUESTIONS.read_text(encoding="utf-8")
numbered = []
section = ""
for line in text.splitlines():
    level = re.match(r"# Level (\d+) - (.+)", line)
    if level:
        section = f"topic{int(level.group(1)):02d}_{re.sub(r'[^a-z0-9]+', '_', level.group(2).lower()).strip('_')}"
    item = re.match(r"(\d+)\. (.+)", line)
    if item:
        numbered.append((int(item.group(1)), item.group(2).strip(), section))

theory_heading = text.index("## Interview Theory Questions")
theory = re.findall(r"^\* (.+)$", text[theory_heading:], re.MULTILINE)

additional = [
    (204, "ADDITIONAL: Lambda expressions and method references together", "topic24_additional"),
    (205, "ADDITIONAL: Demonstrate stream creation, laziness, and short-circuiting", "topic24_additional"),
    (206, "ADDITIONAL: Use takeWhile() on an ordered stream", "topic24_additional"),
    (207, "ADDITIONAL: Use dropWhile() on an ordered stream", "topic24_additional"),
    (208, "ADDITIONAL: Group Products with a downstream toMap merge strategy", "topic24_additional"),
    (209, "ADDITIONAL: Calculate an Order total from its Products", "topic24_additional"),
    (210, "ADDITIONAL: Show primitive-stream boxing and unboxing costs", "topic24_additional"),
    (211, "ADDITIONAL: Demonstrate the stream-reuse interview trap", "topic24_additional"),
]

def class_stem(title: str) -> str:
    title = title.replace("()", "").replace("/", " ").replace("`", "")
    words = re.findall(r"[A-Za-z0-9]+", title)
    stem = "".join(word[:1].upper() + word[1:] for word in words)
    return stem[:90] or "Question"

def safe_doc(value: str) -> str:
    return value.replace("*/", "*&#47;")

entries = numbered + [(181 + i, title, "topic23_interview_theory") for i, title in enumerate(theory)] + additional
if len(numbered) != 180 or len(theory) != 23:
    raise SystemExit(f"Unexpected source counts: numbered={len(numbered)}, theory={len(theory)}")

runner_text = (JAVA_ROOT / "support/PracticeRunner.java").read_text(encoding="utf-8")
solution_by_id = {}
case_pattern = re.compile(
    r"^\s*case\s+([0-9, ]+)\s*->\s*(.*?)(?=^\s*(?:case\s+|default\s+->))",
    re.MULTILINE | re.DOTALL,
)
for match in case_pattern.finditer(runner_text):
    payload = match.group(2).rstrip()
    for raw_id in match.group(1).split(","):
        solution_by_id[int(raw_id.strip())] = (match.group(1).strip(), payload)

theory_answers = [
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
    "Common traps include stream reuse, side effects, unsafe parallel mutation, and forgotten resource closing.",
]

def preamble(question_id: int) -> str:
    if question_id <= 10:
        return "List<Integer> input = numbers();\n        System.out.println(\"Input: \" + input);"
    if 77 <= question_id <= 90:
        return "Stream<String> stream = words().stream();\n        System.out.println(\"Input: \" + words());"
    if 91 <= question_id <= 101:
        return ('List<Student> students = List.of(new Student("Ana", 90), new Student("Bo", 70), new Student("Cy", 90));\n'
                '        List<Person> people = List.of(new Person("Kid", 12), new Person("Adult", 22), new Person("Senior", 65));')
    if 106 <= question_id <= 114:
        return 'Optional<String> value = Optional.of("Apple");'
    if 143 <= question_id <= 180:
        return ('List<Integer> n = numbers();\n        List<String> w = words();\n'
                '        String text = "java streams make java concise streams";')
    return 'System.out.println("Input: deterministic sample data");'

rows = []
for question_id, title, package_dir in entries:
    filename = f"Q{question_id:03d}_{class_stem(title)}.java"
    class_name = filename[:-5]
    directory = JAVA_ROOT / package_dir
    directory.mkdir(parents=True, exist_ok=True)
    source_kind = "ADDITIONAL" if question_id >= 204 else "README source (questions.md)"
    source_ref = (f"questions.md - original question {question_id}" if question_id <= 180
                  else f"questions.md - Interview Theory Question {question_id - 180}" if question_id <= 203
                  else f"PRACTICE_INDEX.md - additional question {question_id - 203}")
    java_title = title.replace("\\", "\\\\").replace('"', '\\"')
    if 181 <= question_id <= 203:
        answer = theory_answers[question_id - 181].replace("\\", "\\\\").replace('"', '\\"')
        solution = f'String result = "{answer}";'
    else:
        if question_id not in solution_by_id:
            raise SystemExit(f"No inline solution found for question {question_id}")
        labels, payload = solution_by_id[question_id]
        indented_payload = textwrap.indent(payload, " " * 12).lstrip()
        solution = (f"Object result = switch ({question_id}) {{\n"
                    f"            case {labels} -> {indented_payload}\n"
                    f"            default -> throw new IllegalStateException(\"Unexpected question\");\n"
                    f"        }};")
    source = f'''package com.dennym55.streams.{package_dir};

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
 * {safe_doc(title)}
 *
 * <p>Input: A small deterministic sample is printed before the operation when applicable.
 * Expected output: The solved result (or concise theory answer) is printed as {{@code Output: ...}}.
 *
 * <p>Source: {safe_doc(source_ref)}
 */
public class {class_name} {{
    public static void main(String[] args) {{
        System.out.println("Question: {java_title}");
        System.out.println("Source: {source_ref}");
        {preamble(question_id)}

        // Complete solution for this question; shared helpers only provide fixtures or reusable setup.
        {solution}
        System.out.println("Output: " + result);
    }}
}}
'''
    (directory / filename).write_text(source, encoding="utf-8", newline="\n")
    rel = f"src/main/java/com/dennym55/streams/{package_dir}/{filename}"
    rows.append((question_id, title, filename, rel, source_kind))

index = [
    "# Java Streams Practice Index",
    "",
    "The original sequence is retained exactly. Repeated questions remain separate and traceable.",
    "Practice in ascending sequence order; supplementary questions come after every original question.",
    "",
    "| Order | Original question / title | Runnable class | Package/folder | Origin |",
    "|---:|---|---|---|---|",
]
for question_id, title, filename, rel, source_kind in rows:
    display_order = str(question_id) if question_id <= 180 else (f"T{question_id - 180:02d}" if question_id <= 203 else f"A{question_id - 203:02d}")
    escaped_title = title.replace("|", "\\|")
    index.append(f"| {display_order} | {escaped_title} | [{filename}]({rel.replace(' ', '%20')}) | `{Path(rel).parent.as_posix()}` | {source_kind} |")
(ROOT / "PRACTICE_INDEX.md").write_text("\n".join(index) + "\n", encoding="utf-8", newline="\n")

readme = """# Java Streams Solved Practice Repository

This Maven/Java 26 project turns the repository's original Streams roadmap into independently runnable solved examples.

The repository did not originally contain a root `README.md`. Its source material remains preserved verbatim in:

- [`questions.md`](src/main/java/streams/questions.md) — the primary question order
- [`answers-anki-mode.md`](src/main/java/streams/answers-anki-mode.md) — the original answer notes

No original question was removed or merged. The 180 numbered questions and 23 interview-theory questions each have a runnable class. Intentional duplicates remain separate. Eight clearly marked `ADDITIONAL` examples fill the remaining requested coverage gaps.

Use the complete clickable [`PRACTICE_INDEX.md`](PRACTICE_INDEX.md) to navigate all 211 classes in recommended order.

## Run

```powershell
mvn test
mvn -q -DskipTests package
```

In IntelliJ IDEA, open any indexed class and run its `public static void main(String[] args)` method. Start with [`Q001_FilterAllOddNumbers.java`](src/main/java/com/dennym55/streams/topic01_stream_basics/Q001_FilterAllOddNumbers.java).

## Design

Each question has a dedicated sequence-prefixed class with the original wording and source reference in JavaDoc. Its complete solution expression is visible directly inside `main`. Only deterministic fixtures, domain records, and genuinely reusable helpers live in `com.dennym55.streams.support`.
"""
(ROOT / "README.md").write_text(readme, encoding="utf-8", newline="\n")
print(f"Generated {len(entries)} launchers: {len(numbered) + len(theory)} original and {len(additional)} additional")
