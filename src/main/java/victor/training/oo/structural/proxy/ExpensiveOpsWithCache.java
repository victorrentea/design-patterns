package victor.training.oo.structural.proxy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

// Decorator
public class ExpensiveOpsWithCache implements ExpensiveOps {
	private final Map<Integer, Boolean> cache = new HashMap<>();
	private final ExpensiveOps expensiveOps;

	public ExpensiveOpsWithCache(ExpensiveOps expensiveOps) {
		this.expensiveOps = expensiveOps;
	}
	public Boolean isPrime(int n) {
		if (cache.containsKey(n)) {
			return cache.get(n);
		}
		Boolean response = expensiveOps.isPrime(n);
		cache.put(n, response);
		return response;
	}

	@Override
	public String hashAllFiles(File folder) {
		return expensiveOps.hashAllFiles(folder);
	}

}
