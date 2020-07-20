//package victor.training.oo.structural.proxy;
//
//import org.springframework.context.annotation.Primary;
//import org.springframework.context.annotation.Profile;
//import org.springframework.stereotype.Service;
//
//import java.io.File;
//import java.util.HashMap;
//import java.util.Map;
//
////record Point(int a, int b) {
////
////}
//
////@Value
////class Point2 {
////	int a;
////	int b;
////}
//
//
//// Decorator
//@Service
//@Primary
//@Profile("!local")
//public class ExpensiveOpsWithCache implements ExpensiveOps {
//	private final Map<Integer, Boolean> cache = new HashMap<>();
//	private final ExpensiveOps expensiveOps;
//
//
//
//
//	class Inner { // iiner - niciodata. Foarte creepy. memory leaks, etc...
//
//	}
//	static class Nested { // nested
//
//	}
//	public ExpensiveOpsWithCache(ExpensiveOps expensiveOps) {
//		this.expensiveOps = expensiveOps;
//	}
//	public Boolean isPrime(int n) {
//		if (cache.containsKey(n)) {
//			return cache.get(n);
//		}
//		Boolean response = expensiveOps.isPrime(n);
//		cache.put(n, response);
//		return response;
//	}
//
//	@Override
//	public String hashAllFiles(File folder) {
//		return expensiveOps.hashAllFiles(folder);
//	}
//
//}
