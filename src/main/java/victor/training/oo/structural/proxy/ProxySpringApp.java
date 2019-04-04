package victor.training.oo.structural.proxy;

import static java.util.Arrays.asList;

import java.io.File;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
@EnableAspectJAutoProxy 
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
	
	@Autowired
	private ExpensiveOps ops;// = ClassProxy.proxy(new ExpensiveOps());
	public void run(String... args) throws Exception {
		holySacredBusinessLogic(ops);
	}

	private void holySacredBusinessLogic(ExpensiveOps ops) {
		log.debug("KGB: who do you for for ? " + ops.getClass());
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

@Retention(RetentionPolicy.RUNTIME)
@interface Facade {}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface Logged {}

// in a distant file, burrowed underneath 10 foreign packages
@Component
@Slf4j
@Aspect
class MyAspect {
		
	@Around("execution(* *(..)) && @annotation(victor.training.oo.structural.proxy.Logged)")
	public Object executeAround(ProceedingJoinPoint joinPoint) throws Throwable {
		log.debug("Call Intercepted: {}({})", joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
		return joinPoint.proceed();
	}
}


