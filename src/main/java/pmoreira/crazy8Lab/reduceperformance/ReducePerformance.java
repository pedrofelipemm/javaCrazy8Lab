package pmoreira.crazy8Lab.reduceperformance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

public class ReducePerformance {

	private static final String LETTER_PATTERN = "[^a-zA-Z]";

	public Long streamSum(final List<Long> numbers) {
		return numbers.stream().reduce((e1, e2) -> e1 + e2).get();
	};

	public Long parallelStreamSum(final List<Long> numbers) {
		return numbers.parallelStream().reduce((e1, e2) -> e1 + e2).get();
	};

	public Long forSum(final List<Long> numbers) {
		Long sum = 0L;
		for (Long l : numbers) {
			sum += l;
		}
		return sum;
	}

	public long streamWordCount(final String file) throws IOException {
		return wordCount(Files.lines(Paths.get(file)));
	}

	public long parallelWordCount(final String file) throws IOException {
		return wordCount(Files.lines(Paths.get(file)).parallel());
	}

	public long forWordCount(final String file) {
		long count = 0;

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				for (String word : line.trim().split(StringUtils.SPACE)) {
					word = word.replaceAll(LETTER_PATTERN, StringUtils.EMPTY).toLowerCase().trim();
					if (word.length() > 0) {
						count++;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return count;
	}

	private long wordCount(final Stream<String> file) {
		return file.flatMap(line -> Arrays.stream(line.trim().split(StringUtils.SPACE)))
				.map(word -> word.replaceAll(LETTER_PATTERN, StringUtils.EMPTY).toLowerCase().trim())
				.filter(word -> word.length() > 0)
				.count();
	}

}
