package pmoreira.crazy8Lab.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public interface MeasurableTest {

	int EXECUTION_AMOUNT = 10;

	default void execute(final String msg, final Method method, final Object obj, final Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println(msg);
		execute(method, obj, args);
	}

	default void execute(final Method method, final Object obj, final Object... args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println(String.format("Executing method: %s", method.getName()));
		long totalTime = 0;
		for (int i = 0; i < EXECUTION_AMOUNT; i++) {
			long start = System.currentTimeMillis();
			Object output = method.invoke(obj, args);
			long end = System.currentTimeMillis() - start;
			System.out.println(String.format("Execution-%d took: %dms | output: %s", i + 1, end, output));
			totalTime += end;
		}
		System.out.println(String.format("Average time: %dms\n\n", totalTime / EXECUTION_AMOUNT));
	};

}
