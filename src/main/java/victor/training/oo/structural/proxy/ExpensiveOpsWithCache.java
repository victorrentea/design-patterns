package victor.training.oo.structural.proxy;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Retention(RetentionPolicy.RUNTIME)
@Qualifier
@interface Cached {
	
}

@Cached
//@Service
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
	
	public void killTheCache() {
		 // TODO
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