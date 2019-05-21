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

	@Autowired
	private ExpensiveOps ops;
	
	// TODO [1] implement decorator 
	// TODO [2] apply decorator via Spring
	// TODO [3] generic java.lang.reflect.Proxy 
	// TODO [4] Spring aspect 
	// TODO [5] Spring cache support
	// TODO [6] Back to singleton (are you still alive?)
	public void run(String... args) throws Exception {
//		IExpensiveOps ops = new ExpensiveOps();
//		ops = new ExpensiveOpsWithCache(ops);

		zenDomainLogicThatIwantToKeepSafeFromAnyInfrastructuralConcerns(ops);


	}

	private void zenDomainLogicThatIwantToKeepSafeFromAnyInfrastructuralConcerns(ExpensiveOps ops) {
		log.debug("\n");
		log.debug("Who are you?! " + ops.getClass());
		log.debug("---- CPU Intensive ~ memoization?");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");

		log.debug("---- I/O Intensive ~ \"There are only two things hard in programming...\"");
		log.debug("Folder MD5: ");
		log.debug("Got: " + ops.hashAllFiles(new File(".")) + "\n");
		log.debug("Got: " + ops.hashAllFiles(new File(".")) + "\n");

		System.out.println("Detect a file change. Should throw away the cache");
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

@Retention(RetentionPolicy.RUNTIME)
@interface  LoggedClass {

}
@Retention(RetentionPolicy.RUNTIME)
@interface  LoggedMethod {

}

@Component
@Aspect
@Slf4j
class LoggingInterceptor {

//	@Around("execution(* victor..*(..))")
//	@Around("execution(* *(..)) && @within(victor.training.oo.structural.proxy.LoggedClass)")
	@Around("execution(* *(..)) && @annotation(victor.training.oo.structural.proxy.LoggedMethod)")
	public Object interceptForLog(ProceedingJoinPoint point) throws Throwable {
		log.debug("Calling method {} with params {}",
				point.getSignature().getName(),
				Arrays.toString(point.getArgs()));
//		Thread.sleep(2000);;
		// connect to the database
		// send SMS
		return point.proceed();
	}
}










@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@interface WithCache {

}