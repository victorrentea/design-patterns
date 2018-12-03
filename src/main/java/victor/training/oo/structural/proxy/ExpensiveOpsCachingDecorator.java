package victor.training.oo.structural.proxy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class ExpensiveOpsCachingDecorator implements IExpensiveOps { 

	private final IExpensiveOps delegate;
	private Map<Integer, Boolean> cache = new HashMap<>(); 
	
	
	public ExpensiveOpsCachingDecorator(IExpensiveOps delegate) {
		this.delegate = delegate;
	}
	
	public Boolean isPrime(int n) {
		if (cache.containsKey(n)) {
			return cache.get(n);
		}
		Boolean prime = delegate.isPrime(n);
		cache.put(n, prime);
		return prime;
	}

	public String hashAllFiles(File folder) {
		return delegate.hashAllFiles(folder); // TODO
	}
	
	
	
}
