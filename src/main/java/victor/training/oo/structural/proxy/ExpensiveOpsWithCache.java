package victor.training.oo.structural.proxy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
//@AiaRapida
public class ExpensiveOpsWithCache implements IExpensiveOps {
	private final IExpensiveOps delegate;
	private Map<Integer, Boolean> cache = new HashMap<>(); 

	public ExpensiveOpsWithCache(IExpensiveOps delegate) {
		this.delegate = delegate;
	}
	
	public Boolean isPrime(int n) { 
		if (cache.containsKey(n)) {
			return cache.get(n);
		}
		Boolean result = delegate.isPrime(n);
		cache.put(n, result);
		return result;
	}

	public String hashAllFiles(File folder) {
		return delegate.hashAllFiles(folder); 
	}
}