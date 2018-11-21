package victor.training.oo.structural.proxy;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpensiveOpsCachingDecorator implements IExpensiveOps{ 

	private final IExpensiveOps delegate;
	
	public ExpensiveOpsCachingDecorator(IExpensiveOps delegate) {
		this.delegate = delegate;
	}

//	private Map<Integer, Boolean> primesCache = new HashMap<>();
	
	public Boolean isPrime(int n) {
		if (cache.containsKey(getCacheKey("isPrime", n))) {
			return (Boolean) cache.get(getCacheKey("isPrime", n));
		}
		Boolean rez = delegate.isPrime(n);
		cache.put(getCacheKey("isPrime", n), rez);
		return rez;
	}
	

	
//	private Map<File, Boolean> folderHashCache = new HashMap<>();
	
	
	public String hashAllFiles(File folder) {
		return delegate.hashAllFiles(folder); // TODO
	}
	
	
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
