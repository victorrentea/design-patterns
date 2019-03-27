package victor.training.oo.structural.proxy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Slf4j
@Component
public class SillyCachingAspect {
	
	@Around("execution(* IExpensiveOps.*(..))")
	public Object logAround(ProceedingJoinPoint point) throws Throwable {
		log.debug("(intercepted)");
		// TODO inspire from Decorator 
		return point.proceed();
	}
	
	// FIXME Note: Faking a cache here :). Such crap code might produce OutOfMemoryErrors.
	private Map<List<?>, Object> cache = new HashMap<>();
	
	private List<Object> getCacheKey(String methodName, Object... args) {
		List<Object> list = new ArrayList<>();
		list.add(methodName);
		list.addAll(Arrays.asList(args));
		return list;
	}
}