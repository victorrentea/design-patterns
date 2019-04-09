package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jooq.lambda.Unchecked;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InterfaceProxy {

	public static IExpensiveOps proxy(ExpensiveOps realImplem) {
		
		InvocationHandler h = new InvocationHandler() {
			
			private Map<List<Object>, Object> cache = new ConcurrentHashMap<>(); 
			
			private List<Object> getCacheKey(Method method, Object... args) {
				List<Object> list = new ArrayList<>();
				list.add(method.getName());
				list.addAll(Arrays.asList(args));
				return list;
			}
			
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				List<Object> key = getCacheKey(method, args);
				log.debug("Intercepted: " + method.getName());
				return cache.computeIfAbsent(key, Unchecked.function(unused -> method.invoke(realImplem, args)));
				
			}
		};
		
		return (IExpensiveOps) Proxy.newProxyInstance(InterfaceProxy.class.getClassLoader(), 
				new Class<?>[] {IExpensiveOps.class}, h); 
	}

}
