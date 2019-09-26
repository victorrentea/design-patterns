package victor.training.oo.structural.proxy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ExpesiveOpsWithCache implements IExpensiveOps  {
	private final IExpensiveOps ops;
	
	static Map<Integer, Boolean> cache = new HashMap<>();
	
	public ExpesiveOpsWithCache(IExpensiveOps ops) {
		this.ops = ops;
	}

	public Boolean isPrime(int n) {
		if (cache.containsKey(n)) {
			return cache.get(n);
		}
		Boolean rez = ops.isPrime(n);
		cache.put(n, rez);
		return rez;
	}

	public String hashAllFiles(File folder) {
		return ops.hashAllFiles(folder);
	}
	
}