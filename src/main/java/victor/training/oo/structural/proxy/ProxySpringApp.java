package victor.training.oo.structural.proxy;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableCaching 
@SpringBootApplication
public class ProxySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ProxySpringApp.class, args);
	}

	
	// TODO [1] implement decorator 
	// TODO [2] apply decorator via Spring
	// TODO [3] generic java.lang.reflect.Proxy 
	// TODO [4] Spring aspect 
	// TODO [5] Spring cache support
	// TODO [6] Back to singleton (are you still alive?)
	// TODO [7] AopContext.currentProxy();

//	@Autowired 
//	@Qualifier("expensiveOpsWithCache")
//	@WithCache
	
	
	
	@Autowired
	private ExpensiveOps ops;// = ClassProxy.proxy(new ExpensiveOps());
	
	// Holy Domain Logic. 
	// Very precious things that I want to keep agnostic to technical details
	public void run(String... args) throws Exception {
		log.debug("Whoaare you !? " + ops.getClass());
		log.debug("\n");
		log.debug("---- CPU Intensive ~ memoization?");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10_000_169) + "\n");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10_000_169) + "\n");
		log.debug("Got: " + ops.isPrime(10_000_168) + "\n");
		
		log.debug("---- I/O Intensive ~ \"There are only two things hard in programming...\"");
		log.debug("Folder MD5: ");
		log.debug("Got: " + ops.hashAllFiles(new File(".")) + "\n");
		log.debug("Got: " + ops.hashAllFiles(new File(".")) + "\n");
		
		// Suppose I detect a change in a folder
		log.debug("I must throw away the cache for folder .");
		ops.invalidateCache(new File("."));
		
		log.debug("Folder MD5: ");
		log.debug("Got: " + ops.hashAllFiles(new File(".")) + "\n");
	}
}

//@Qualifier
//@Retention(RetentionPolicy.RUNTIME)
//@interface WithCache {}
//
////@Profile("withCache")
////@Primary
//@WithCache
//@Service
//class ExpensiveOpsWithCache implements IExpensiveOps { 
//	private final IExpensiveOps delegate;
//	private Map<Integer, Boolean> cache = new HashMap<>();
//	
//	public ExpensiveOpsWithCache(IExpensiveOps delegate) {
//		this.delegate = delegate;
//	}
//
//	public Boolean isPrime(int n) {
//		return cache.computeIfAbsent(n, delegate::isPrime);
//	}
//
//	public String hashAllFiles(File folder) {
//		return delegate.hashAllFiles(folder);
//	}
//}


@Component
@Retention(RetentionPolicy.RUNTIME)
@interface Facade {}


// in a distant file, burrowed underneath 10 foreign packages
@Component
@Slf4j
@Aspect
class MyAspect {
		
	@Around("execution(* *(..)) && @within(victor.training.oo.structural.proxy.Facade)")
	public Object executeAround(ProceedingJoinPoint joinPoint) throws Throwable {
		log.debug("Call Intercepted: {}({})", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
		return joinPoint.proceed();
	}
}


