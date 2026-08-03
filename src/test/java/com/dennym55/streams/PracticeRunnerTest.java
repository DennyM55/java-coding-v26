package com.dennym55.streams;

import com.dennym55.streams.support.PracticeRunner;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PracticeRunnerTest {
    @Test
    void everyOriginalAndAdditionalSolutionRuns() {
        PrintStream original = System.out;
        ByteArrayOutputStream captured = new ByteArrayOutputStream();
        try {
            System.setOut(new PrintStream(captured, true, StandardCharsets.UTF_8));
            for (int id = 1; id <= 211; id++) {
                PracticeRunner.run(id, "verification", "automated test");
            }
        } finally {
            System.setOut(original);
        }
        assertTrue(captured.toString(StandardCharsets.UTF_8).contains("Output:"));
    }
}
