package victor.training.oo.structural.proxy;

import static java.util.Arrays.asList;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@interface AiaRapida {
}
@Retention(RetentionPolicy.RUNTIME)
@interface LoggedClass {
}

@Slf4j
@EnableAspectJAutoProxy(exposeProxy = true)
@EnableCaching 
@SpringBootApplication
public class ProxySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ProxySpringApp.class, args);
	}

	@Autowired
//	@AiaRapida
	private ExpensiveOps ops;
	
	// TODO [1] implement decorator 
	// TODO [2] apply decorator via Spring
	// TODO [3] generic java.lang.reflect.Proxy 
	// TODO [4] Spring aspect 
	// TODO [5] Spring cache support
	// TODO [6] Back to singleton (are you still alive?)
	public void run(String... args) throws Exception {
		log.debug("oare cine imi ruleaza mie logul acela ? SRIul: " + ops.getClass());
		logicaDeDomeniu();
	}

// Holy Domain Logic. 
// Very precious things that I want to keep agnostic to technical details
	private void logicaDeDomeniu() {
		log.debug("\n");
		log.debug("---- CPU Intensive ~ memoization?");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");
		
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
	
	private static List<Object> getCacheKey(String methodName, Object... args) {
		List<Object> list = new ArrayList<>();
		list.add(methodName);
		list.addAll(asList(args));
		return list;
	}
}
@Slf4j
@Aspect
@Component
class LoggingAspect {
	
//	@Around("execution(* victor..*(..))")
	@Around("execution(* *(..)) && @within(victor.training.oo.structural.proxy.LoggedClass)")
	public Object intercepteazo(ProceedingJoinPoint point) throws Throwable {
		log.debug("Computing {}({})", point.getSignature().getName(), Arrays.toString(point.getArgs())); //
		return point.proceed();
	}
	
}
