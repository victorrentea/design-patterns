package victor.training.oo.structural.proxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jooq.lambda.Unchecked;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClassProxy {

	public static ExpensiveOps proxy(ExpensiveOps realImplem) {
		Callback callback = new MethodInterceptor() {
			
			
			private Map<List<Object>, Object> cache = new ConcurrentHashMap<>(); 
			
			private List<Object> getCacheKey(Method method, Object... args) {
				List<Object> list = new ArrayList<>();
				list.add(method.getName());
				list.addAll(Arrays.asList(args));
				return list;
			}
			public Object intercept(Object arg0, Method method, Object[] args, MethodProxy arg3) throws Throwable {
				List<Object> key = getCacheKey(method, args);
				log.debug("Intercepted: " + method.getName());
				return cache.computeIfAbsent(key, Unchecked.function(unused -> method.invoke(realImplem, args)));
				
			}
		};
		return (ExpensiveOps) Enhancer.create(ExpensiveOps.class, callback);
	}

}
