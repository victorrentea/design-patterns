package victor.training.oo.structural.proxy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
@Primary
@Service
public class ExpensiveOpsWithCache implements IExpensiveOps  {
	
	private final IExpensiveOps ops;
	
	public ExpensiveOpsWithCache(IExpensiveOps ops) {
		this.ops = ops;
	}

	private static Map<Object, Object> cache = new HashMap<>();
	
	public Boolean isPrime(int n) {
		if (cache.containsKey(n)) {
			return (Boolean) cache.get(n);
		}
		Boolean result = ops.isPrime(n);
		cache.put(n, result);
		return result;
	}
	
	
	public String hashAllFiles(File folder) {
		if (cache.containsKey(folder)) {
			return (String) cache.get(folder);
		}
		String result = ops.hashAllFiles(folder);
		cache.put(folder, result);
		return result;
	}
}