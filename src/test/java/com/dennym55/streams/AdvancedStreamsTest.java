package com.dennym55.streams;

import com.dennym55.streams.support.PracticeData.Employee;
import com.dennym55.streams.support.PracticeData.Order;
import com.dennym55.streams.support.PracticeData.Product;
import com.dennym55.streams.topic21_parallel_streams.Q141_ThreadSafety;
import com.dennym55.streams.topic22_real_interview_questions.Q179_CustomCollector;
import com.dennym55.streams.topic25_advanced_functional_programming.Q212_LambdaClosuresEffectivelyFinalAndHigherOrderFunctions;
import com.dennym55.streams.topic25_advanced_functional_programming.Q213_ImmutabilityPureFunctionsAndSideEffectPitfalls;
import com.dennym55.streams.topic26_advanced_stream_creation.Q214_AdvancedStreamFactories;
import com.dennym55.streams.topic27_advanced_collectors.Q215_DownstreamFilteringCollector;
import com.dennym55.streams.topic27_advanced_collectors.Q216_DownstreamFlatMappingCollector;
import com.dennym55.streams.topic27_advanced_collectors.Q217_TeeingCollector;
import com.dennym55.streams.topic27_advanced_collectors.Q218_NestedGroupingAndDownstreamCollectors;
import com.dennym55.streams.topic28_spliterators.Q219_CustomBatchingSpliteratorTryAdvance;
import com.dennym55.streams.topic28_spliterators.Q220_SpliteratorTrySplitAndCharacteristics;
import com.dennym55.streams.topic29_parallelism_and_benchmarking.Q221_ParallelOrderingForkJoinPoolAndSafeReduction;
import com.dennym55.streams.topic29_parallelism_and_benchmarking.Q222_JmhLoopVsSequentialVsParallelBenchmark;
import com.dennym55.streams.topic30_gatherers.Q223_GathererFixedAndSlidingWindows;
import com.dennym55.streams.topic30_gatherers.Q224_GathererFoldAndScan;
import com.dennym55.streams.topic30_gatherers.Q225_CustomGathererCollapseConsecutiveDuplicates;
import com.dennym55.streams.topic31_advanced_interview_traps.Q226_AdvancedStreamInterviewTraps;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;

import static com.dennym55.streams.support.PracticeData.employees;
import static org.junit.jupiter.api.Assertions.*;

class AdvancedStreamsTest {

    @Test
    void closureAndHigherOrderFunctionComposeCorrectly() {
        assertEquals(List.of(22, 24, 26),
                Q212_LambdaClosuresEffectivelyFinalAndHigherOrderFunctions.transform(List.of(1, 2, 3), 10));
        assertEquals(-2, Q212_LambdaClosuresEffectivelyFinalAndHigherOrderFunctions.addOffset(-5).applyAsInt(3));
    }

    @Test
    void purePipelineDoesNotMutateInputAndReturnsUnmodifiableResult() {
        List<Integer> input = new ArrayList<>(List.of(-1, 1, 2));
        List<Integer> result = Q213_ImmutabilityPureFunctionsAndSideEffectPitfalls.squaredPositives(input);
        assertEquals(List.of(-1, 1, 2), input);
        assertEquals(List.of(1, 4), result);
        assertThrows(UnsupportedOperationException.class, () -> result.add(9));
    }

    @Test
    void advancedStreamFactoriesHandleNullAndBoundIteration() {
        var nullResult = Q214_AdvancedStreamFactories.create(null);
        assertTrue(nullResult.nullable().isEmpty());
        assertEquals(List.of("A", "B", "C"), nullResult.concatenated());
        assertEquals(List.of("x", "y"), nullResult.built());
        assertEquals(List.of(1, 2, 4, 8, 16), nullResult.boundedIteration());
        assertEquals(List.of("value"), Q214_AdvancedStreamFactories.create("value").nullable());
    }

    @Test
    void downstreamFilteringRetainsEmptyGroups() {
        Map<String, List<Employee>> result =
                Q215_DownstreamFilteringCollector.highEarnersByDepartment(employees(), 90_000);
        assertEquals(List.of("Chen"), result.get("IT").stream().map(Employee::name).toList());
        assertEquals(List.of(), result.get("HR"));
        assertEquals(List.of(), result.get("Sales"));
    }

    @Test
    void downstreamFlatMappingFlattensAllOrdersPerCustomer() {
        Product a = new Product(1, "A", "One", 1);
        Product b = new Product(2, "B", "Two", 2);
        List<Order> orders = List.of(new Order(1, "customer", List.of(a)),
                new Order(2, "customer", List.of(a, b)));
        assertEquals(List.of("A", "B"),
                Q216_DownstreamFlatMappingCollector.productNamesByCustomer(orders)
                        .get("customer").stream().toList());
    }

    @Test
    void teeingHandlesPopulatedAndEmptyInputs() {
        var summary = Q217_TeeingCollector.summarize(employees());
        assertEquals(6, summary.count());
        assertEquals(404_000, summary.total());
        var empty = Q217_TeeingCollector.summarize(List.of());
        assertEquals(0, empty.count());
        assertEquals(0.0, empty.average());
    }

    @Test
    void nestedGroupingUsesBothClassifiersAndDownstreamMapping() {
        var result = Q218_NestedGroupingAndDownstreamCollectors.group(employees());
        assertEquals(List.of("Asha"), result.get("IT").get("UNDER_30").stream().toList());
        assertEquals(List.of("Ben"), result.get("HR").get("40_PLUS").stream().toList());
    }

    @Test
    void fullCustomCollectorWorksSequentiallyInParallelAndWhenEmpty() {
        List<String> input = List.of("a", "bbbb", "cc");
        var sequential = Q179_CustomCollector.summarize(input);
        var parallel = input.parallelStream().collect(Q179_CustomCollector.wordStatisticsCollector());
        assertEquals(sequential, parallel);
        assertEquals(new Q179_CustomCollector.WordStatistics(3, 7, "bbbb", 7.0 / 3), sequential);
        assertEquals(new Q179_CustomCollector.WordStatistics(0, 0, "", 0.0),
                Q179_CustomCollector.summarize(List.of()));
    }

    @Test
    void improvedThreadSafetyExampleHasTwoCorrectStrategies() {
        Q141_ThreadSafety.Counts counts = Q141_ThreadSafety.count(20_000);
        assertEquals(20_000, counts.atomicCounter());
        assertEquals(20_000, counts.reductionCount());
        assertTrue(counts.unsafeSharedCounter() <= 20_000);
    }

    @Test
    void batchingSpliteratorHandlesPartialEmptyAndInvalidBatches() {
        assertEquals(List.of(List.of(1, 2), List.of(3, 4), List.of(5)),
                Q219_CustomBatchingSpliteratorTryAdvance.batches(List.of(1, 2, 3, 4, 5), 2));
        assertEquals(List.of(), Q219_CustomBatchingSpliteratorTryAdvance.batches(List.of(), 3));
        assertThrows(IllegalArgumentException.class,
                () -> Q219_CustomBatchingSpliteratorTryAdvance.batches(List.of(1), 0));
    }

    @Test
    void rangeSpliteratorSplitsAndPreservesSizedContract() {
        var range = new Q220_SpliteratorTrySplitAndCharacteristics.IntRangeSpliterator(0, 9);
        Spliterator.OfInt prefix = range.trySplit();
        assertEquals(4, prefix.estimateSize());
        assertEquals(5, range.estimateSize());
        assertTrue(range.hasCharacteristics(Spliterator.SIZED | Spliterator.SUBSIZED | Spliterator.SORTED));
        assertEquals(45, Q220_SpliteratorTrySplitAndCharacteristics.parallelSum(0, 10));
        assertEquals(0, Q220_SpliteratorTrySplitAndCharacteristics.parallelSum(5, 5));
    }

    @Test
    void customForkJoinPoolKeepsCorrectOrderAndReduction() {
        var result = Q221_ParallelOrderingForkJoinPoolAndSafeReduction.process(100, 2);
        assertEquals(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), result.orderedPrefix());
        assertEquals(5_050, result.sum());
        assertFalse(result.workerThreads().isEmpty());
    }

    @Test
    void benchmarkStrategiesComputeTheSameValue() {
        var benchmark = new Q222_JmhLoopVsSequentialVsParallelBenchmark();
        benchmark.setup();
        assertEquals(benchmark.loopSum(), benchmark.sequentialStreamSum());
        assertEquals(benchmark.loopSum(), benchmark.parallelStreamSum());
    }

    @Test
    void builtInWindowGatherersCoverPartialAndEmptyInputs() {
        var result = Q223_GathererFixedAndSlidingWindows.windows(List.of(1, 2, 3, 4, 5), 3);
        assertEquals(List.of(List.of(1, 2, 3), List.of(4, 5)), result.fixed());
        assertEquals(List.of(List.of(1, 2, 3), List.of(2, 3, 4), List.of(3, 4, 5)), result.sliding());
        assertTrue(Q223_GathererFixedAndSlidingWindows.windows(List.of(), 2).fixed().isEmpty());
        assertThrows(IllegalArgumentException.class,
                () -> Q223_GathererFixedAndSlidingWindows.windows(List.of(1), 0));
    }

    @Test
    void foldAndScanDifferOnOutputCardinality() {
        assertEquals(new Q224_GathererFoldAndScan.FoldAndScan(10, List.of(1, 3, 6, 10)),
                Q224_GathererFoldAndScan.calculate(List.of(1, 2, 3, 4)));
        assertEquals(new Q224_GathererFoldAndScan.FoldAndScan(0, List.of()),
                Q224_GathererFoldAndScan.calculate(List.of()));
    }

    @Test
    void customGathererOnlyCollapsesConsecutiveDuplicates() {
        assertEquals(List.of("A", "B", "A"),
                Q225_CustomGathererCollapseConsecutiveDuplicates.collapse(
                        Arrays.asList("A", "A", "B", "B", "A")));
        assertEquals(Arrays.asList(null, "A", null),
                Q225_CustomGathererCollapseConsecutiveDuplicates.collapse(
                        Arrays.asList(null, null, "A", "A", null)));
    }

    @Test
    void interviewTrapFixesAreExplicitAndFinite() {
        var result = Q226_AdvancedStreamInterviewTraps.solve(Arrays.asList("A", null, "B"));
        assertEquals(List.of(3L, 3L), result.reusableCounts());
        assertEquals(List.of("A", "B"), result.nonNullValues());
        assertEquals(List.of("A", "B", "C"), result.explicitlyMutable());
        assertThrows(UnsupportedOperationException.class, () -> result.unmodifiable().add("C"));
        assertEquals(List.of(0, 2, 4, 6, 8), result.finiteFromInfinite());
    }
}
