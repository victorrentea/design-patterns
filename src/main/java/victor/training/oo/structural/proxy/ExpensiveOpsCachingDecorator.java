package victor.training.oo.structural.proxy;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class ExpensiveOpsCachingDecorator implements IExpensiveOps{ 

	private final IExpensiveOps delegate;
	
	public ExpensiveOpsCachingDecorator(IExpensiveOps delegate) {
		this.delegate = delegate;
	}

	// FIXME Note: Faking a cache here :). Such crap code might produce OutOfMemoryErrors.
	private Map<List<?>, Object> cache = new HashMap<>(); // INITIAL 
	
	
	

	public Boolean isPrime(int n) {
		return (Boolean) cache.computeIfAbsent(
				getCacheKey("isPrime", n), 
				k -> delegate.isPrime(n));
	}
	
	public String hashAllFiles(File folder) {
		return (String) cache.computeIfAbsent(
				getCacheKey("hashAllFiles", folder), 
				k -> delegate.hashAllFiles(folder));
	}
	
	
	
	
	
	// Hint : cache.computeIfAbsent(getCacheKey("isPrime", number), k -> delegate.isPrime(number));

	private List<Object> getCacheKey(String methodName, Object... args) {
		List<Object> list = new ArrayList<>();
		list.add(methodName);
		list.addAll(Arrays.asList(args));
		return list;
	}






	
}
