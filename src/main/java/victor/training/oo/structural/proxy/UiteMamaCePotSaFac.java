package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UiteMamaCePotSaFac {

	
	public static void main(String[] args) {
		
		ExpensiveOps aiaReala = new ExpensiveOps();
		
		IExpensiveOps falsInActe = imbracaInCache(aiaReala);
		
		System.out.println("Oare 2 e prim? " + falsInActe.isPrime(2));
		System.out.println("Oare 3 e prim? " + falsInActe.isPrime(3));
		System.out.println("Oare 4 e prim? " + falsInActe.isPrime(4));
	}

	public static IExpensiveOps imbracaInCache(ExpensiveOps aiaReala) {
		InvocationHandler h = new InvocationHandler() {
			
			// FIXME Note: Faking a cache here :). Such crap code might produce OutOfMemoryErrors.
			private Map<List<?>, Object> cache = new HashMap<>();
			
			private List<Object> getCacheKey(String methodName, Object... args) {
				List<Object> list = new ArrayList<>();
				list.add(methodName);
				list.addAll(Arrays.asList(args));
				return list;
			}
			
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("invoc metoda "  + method.getName() + " cu parametrii " + Arrays.toString(args));
				List<Object> cacheKey = getCacheKey(method.getName(), args);
				if (cache.containsKey(cacheKey)) {
					return cache.get(cacheKey);
				}
				Object rez = method.invoke(aiaReala, args);
				cache.put(cacheKey, rez);
				return rez; // TODO
			}
		};
		
		IExpensiveOps falsInActe = (IExpensiveOps) Proxy.newProxyInstance(
				UiteMamaCePotSaFac.class.getClassLoader(),
				new Class<?>[] {IExpensiveOps.class}, h);
		return falsInActe;
	}
	
	
}
