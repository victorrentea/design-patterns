package victor.training.oo.structural.proxy;

import static java.util.Arrays.asList;

import java.io.File;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableAspectJAutoProxy 
@EnableCaching 
@SpringBootApplication
public class ProxySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ProxySpringApp.class, args);
	}

	
	// [1] implement decorator 
	// [2] apply decorator via Spring
	// [3] generic java.lang.reflect.Proxy 
	// TODO [4] Spring aspect 
	// TODO [5] Spring cache support
	// TODO [6] Back to singleton (are you still alive?)
	public void run(String... args) throws Exception {
		someDomainLogicCodeIDontWantToTouch();
	}
	
//	@Autowired
//	private IExpensiveOps ops;


	private void someDomainLogicCodeIDontWantToTouch() {
		
		
		
//		InvocationHandler h = (proxy, method, args) -> {
//				return null; // TODO
//		};
		ExpensiveOps realOps = new ExpensiveOps();
		
		InvocationHandler h = new InvocationHandler() {
			private Map<List<?>, Object> cache = new HashMap<>(); // INITIAL 

			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return cache.computeIfAbsent(
						getCacheKey(method.getName(), args), 
						k -> {
							try {
								return method.invoke(realOps, args);
							} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
								throw new RuntimeException(e);
							}
						});
			}
		};
		IExpensiveOps ops = (IExpensiveOps) Proxy.newProxyInstance(
				IExpensiveOps.class.getClassLoader(), 
				new Class<?>[] {IExpensiveOps.class}, 
				h);
		
		
		
		
		
		
		log.debug("\n");
		log.debug("---- CPU Intensive ~ memoization?");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");
		
		log.debug("---- I/O Intensive ~ \"There are only two things hard in programming...\"");
		log.debug("Folder MD5: ");
		log.debug("Got: " + ops.hashAllFiles(new File(".")) + "\n");
		log.debug("Folder MD5: ");
		log.debug("Got: " + ops.hashAllFiles(new File(".")) + "\n");
	}
	
	private static List<Object> getCacheKey(String methodName, Object... args) {
		List<Object> list = new ArrayList<>();
		list.add(methodName);
		list.addAll(asList(args));
		return list;
	}
}