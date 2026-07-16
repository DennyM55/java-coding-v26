# Java Streams Solved Practice Repository

This Maven/Java 26 project turns the repository's original Streams roadmap into independently runnable solved examples.

The repository did not originally contain a root `README.md`. Its source material remains preserved verbatim in:

- [`questions.md`](src/main/java/streams/questions.md) â€” the primary question order
- [`answers-anki-mode.md`](src/main/java/streams/answers-anki-mode.md) â€” the original answer notes

No original question was removed or merged. The 180 numbered questions and 23 interview-theory questions each have a runnable class. Intentional duplicates remain separate. Eight clearly marked `ADDITIONAL` examples fill the remaining requested coverage gaps.

Use the complete clickable [`PRACTICE_INDEX.md`](PRACTICE_INDEX.md) to navigate all 211 classes in recommended order.

## Run

```powershell
mvn test
mvn -q -DskipTests package
```
