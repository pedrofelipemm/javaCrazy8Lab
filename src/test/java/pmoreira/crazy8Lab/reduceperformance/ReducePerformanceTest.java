package pmoreira.crazy8Lab.reduceperformance;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.junit.Test;

import pmoreira.crazy8Lab.utils.MeasurableTest;

public class ReducePerformanceTest implements MeasurableTest {

	private static final long LIST_SIZE = 10_000_000;
	private static final String TEST_FILE = "src/main/resources/testFile.txt";
	private static final String TEST_FILE2 = "src/main/resources/testFile2.txt";

	private final ReducePerformance reduce = new ReducePerformance();
	private final List<Long> numbers = LongStream.rangeClosed(0, LIST_SIZE).boxed().collect(Collectors.toList());

	@Test
	public void streamSumTest() throws Exception {
		execute("List<Long> size: 10.000.000", ReducePerformance.class.getMethod("streamSum", List.class), reduce, numbers);
	}

	@Test
	public void parallelStreamSumTest() throws Exception {
		execute("List<Long> size: 10.000.000", ReducePerformance.class.getMethod("parallelStreamSum", List.class), reduce, numbers);
	}

	@Test
	public void forSumTest() throws Exception {
		execute("List<Long> size: 10.000.000", ReducePerformance.class.getMethod("forSum", List.class), reduce, numbers);
	}

	@Test
	public void stream9476WordsCountTest() throws Exception {
		execute("File with 9.476 words", ReducePerformance.class.getMethod("streamWordCount", String.class), reduce, TEST_FILE);
	}

	@Test
	public void parallel9476WordsTest() throws Exception {
		execute("File with 9.476 words", ReducePerformance.class.getMethod("parallelWordCount", String.class), reduce, TEST_FILE);
	}

	@Test
	public void for9476WordsCountTest() throws Exception {
		execute("File with 9.476 words", ReducePerformance.class.getMethod("forWordCount", String.class), reduce, TEST_FILE);
	}

	@Test
	public void stream10218960WordsCountTest() throws Exception {
		execute("File with 10.218.960 words", ReducePerformance.class.getMethod("streamWordCount", String.class), reduce, TEST_FILE2);
	}

	@Test
	public void parallel10218960WordsTest() throws Exception {
		execute("File with 10.218.960 words", ReducePerformance.class.getMethod("parallelWordCount", String.class), reduce, TEST_FILE2);
	}

	@Test
	public void for10218960WordsCountTest() throws Exception {
		execute("File with 10.218.960 words", ReducePerformance.class.getMethod("forWordCount", String.class), reduce, TEST_FILE2);
	}

}
