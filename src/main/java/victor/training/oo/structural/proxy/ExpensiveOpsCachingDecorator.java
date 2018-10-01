package victor.training.oo.structural.proxy;

import static java.util.Arrays.asList;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//public class ExpensiveMathCachingProxy { // INITIAL
//	private Map<List<?>, Object> cache = new HashMap<>(); // INITIAL 
@Component
@Primary
public class ExpensiveOpsCachingDecorator implements IExpensiveOps { // SOLUTION (

	private final IExpensiveOps delegate;
// Note: Silly cache implementation. Such crap code might produce OutOfMemoryErrors.
	private Map<List<?>, Object> cache = new HashMap<>(); 
	
	public ExpensiveOpsCachingDecorator(IExpensiveOps delegate) {
		this.delegate = delegate;
	}

	public boolean isPrime(int number) {
		return (boolean) cache.computeIfAbsent(getCacheKey("isPrime", number), k -> delegate.isPrime(number));
	}
	public String hashAllProjectFiles(File folder) {
		return  (String) cache.computeIfAbsent(getCacheKey("files", folder), k -> delegate.hashAllProjectFiles(folder));
	}
	
	// SOLUTION )

	private List<Object> getCacheKey(String methodName, Object... args) {
		List<Object> list = new ArrayList<>();
		list.add(methodName);
		list.addAll(asList(args));
		return list;
	}
	
}
