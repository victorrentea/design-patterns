package victor.training.oo.structural.proxy;

import static java.util.Arrays.asList;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
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

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

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
	public void run(String... args) throws Exception {
		holyDomainLogic();
	}
	@Autowired
//	@Cached
	private ExpensiveOps ops;

	private void holyDomainLogic() {
		log.debug("Who the heck are you ? " + ops.getClass());
		log.debug("\n");
		log.debug("---- CPU Intensive ~ memoization?");
		log.debug("4 is prime ? ");
		log.debug("Got: " + ops.isPrime(4) + "\n");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");

		log.debug("---- I/O Intensive ~ \"There are only two things hard in programming...\"");
		log.debug("Folder MD5: ");
		log.debug("Got: " + ops.hashAllFiles(new File(".")) + "\n");
		log.debug("Got: " + ops.hashAllFiles(new File(".")) + "\n");

		log.debug("Detect here a file changed");
		ops.killCache(new File("."));
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

//@Retention(RetentionPolicy.RUNTIME)
//@Qualifier
//@interface Cached {
//
//}

@Retention(RetentionPolicy.RUNTIME)
@interface LoggedClass {
}

@Retention(RetentionPolicy.RUNTIME)
@interface LoggedMethod {
}

@Slf4j
@Aspect
@Component
class LoggerAspect {
//	@Around("execution(* victor..*(..))")
//	@Around("execution(* *(..)) && @within(victor.training.oo.structural.proxy.LoggedClass)")
	@Around("execution(* *(..)) && @annotation(victor.training.oo.structural.proxy.LoggedMethod)")
	public Object interceptCallForLogging(ProceedingJoinPoint point) throws Throwable {
		log.debug(">> Calling method {}", point.getSignature().getName());
		return point.proceed();
	}
}