//package victor.training.oo.structural.proxy;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.context.annotation.Primary;
//import org.springframework.stereotype.Service;
//
//
//@Primary
//@Service
//public class ExpensiveOpsCachingDecorator implements IExpensiveOps { 
//	
//	private final IExpensiveOps delegate;
//	private Map<Integer, Boolean> cacheBabesc = new HashMap<>();
//	
//	
//	public ExpensiveOpsCachingDecorator(IExpensiveOps delegate) {
//		this.delegate = delegate;
//	}
//
//	public Boolean isPrime(int n) {
//		if (cacheBabesc.containsKey(n)) {
//			return cacheBabesc.get(n);
//		}
//		Boolean rez = delegate.isPrime(n);
//		cacheBabesc.put(n, rez);
//		return rez;
//	}
//	
//	
//	public String hashAllFiles(File folder) {
//		return delegate.hashAllFiles(folder); // TODO
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	// FIXME Note: Faking a cache here :). Such crap code might produce OutOfMemoryErrors.
//	private Map<List<?>, Object> cache = new HashMap<>(); // INITIAL 
//
//	// Hint : cache.computeIfAbsent(getCacheKey("isPrime", number), k -> delegate.isPrime(number));
//
//	private List<Object> getCacheKey(String methodName, Object... args) {
//		List<Object> list = new ArrayList<>();
//		list.add(methodName);
//		list.addAll(Arrays.asList(args));
//		return list;
//	}
//	
//}
