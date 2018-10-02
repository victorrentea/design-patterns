package victor.training.oo.structural.proxy;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//public class ExpensiveOpsCachingDecorator { // INITIAL
//	private Map<List<?>, Object> cache = new HashMap<>(); // INITIAL 
// Hint : cache.computeIfAbsent(getCacheKey("isPrime", number), k -> delegate.isPrime(number));
// SOLUTION (
//@Component
//@Primary
public class ExpensiveOpsCachingDecorator implements IExpensiveOps { 

	private final IExpensiveOps delegate;
	
	// FIXME Note: Silly cache implementation. Such crap code might produce OutOfMemoryErrors.
	private Map<List<?>, Object> cache = new HashMap<>(); 
	
	public ExpensiveOpsCachingDecorator(IExpensiveOps delegate) {
		this.delegate = delegate;
	}

	public boolean isPrime(int number) {
		return (boolean) cache.computeIfAbsent(getCacheKey("isPrime", number), k -> delegate.isPrime(number));
	}
	public String hashAllFiles(File folder) {
		return  (String) cache.computeIfAbsent(getCacheKey("files", folder), k -> delegate.hashAllFiles(folder));
	}
	
	// SOLUTION )

	private List<Object> getCacheKey(String methodName, Object... args) {
		List<Object> list = new ArrayList<>();
		list.add(methodName);
		list.addAll(Arrays.asList(args));
		return list;
	}
	
}
