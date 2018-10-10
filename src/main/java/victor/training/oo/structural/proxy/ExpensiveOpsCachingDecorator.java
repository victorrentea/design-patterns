package victor.training.oo.structural.proxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpensiveOpsCachingDecorator { 
	// FIXME Note: Faking a cache here :). Such crap code might produce OutOfMemoryErrors.
	private Map<List<?>, Object> cache = new HashMap<>(); // INITIAL 

	// Hint : cache.computeIfAbsent(getCacheKey("isPrime", number), k -> delegate.isPrime(number));

	private List<Object> getCacheKey(String methodName, Object... args) {
		List<Object> list = new ArrayList<>();
		list.add(methodName);
		list.addAll(Arrays.asList(args));
		return list;
	}
	
}
