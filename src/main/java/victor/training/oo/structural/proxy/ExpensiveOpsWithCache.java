package victor.training.oo.structural.proxy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

//@CuCache
//@Service
public class ExpensiveOpsWithCache implements IExpensiveOps {
	private final IExpensiveOps ops;
	
	public ExpensiveOpsWithCache(IExpensiveOps ops) {
		this.ops = ops;
	}
	private static final Map<Object, Object> cache = new HashMap<>();
	
	public Boolean isPrime(int n) {
		return (Boolean) cache.computeIfAbsent(n, nn-> ops.isPrime((int) nn));
	}
	public String hashAllFiles(File folder) {
		return (String) cache.computeIfAbsent(folder, x-> ops.hashAllFiles((File) x));
	}
}