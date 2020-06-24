package victor.training.oo.structural.proxy;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Arrays;

@Slf4j
@EnableCaching(order = 2)
@SpringBootApplication
public class ProxySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ProxySpringApp.class, args);
	}

	@Autowired IExpensiveOps expensiveOps;
	
	// TODO [1] implement decorator 
	// TODO [2] apply decorator via Spring
	// TODO [3] generic java.lang.reflect.Proxy 
	// TODO [4] Spring aspect 
	// TODO [5] Spring cache support
	// TODO [6] Back to singleton (are you still alive?)
	public void run(String... args) throws Exception {
//		IExpensiveOps expensiveOps = new ExpensiveOps();
//		ops = new ExpensiveOpsWithCache(ops);

		log.debug("Oare ce mi-o dat Springu pe post de expensiveOps? " + expensiveOps.getClass());

		log.debug("\n");
		log.debug("---- CPU Intensive ~ memoization?");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + expensiveOps.isPrime(10000169) + "\n");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + expensiveOps.isPrime(10000169) + "\n");
		
		log.debug("---- I/O Intensive ~ \"There are only two things hard in programming...\"");
		log.debug("Folder MD5: ");
		log.debug("Got: " + expensiveOps.hashAllFiles(new File(".")) + "\n");
		log.debug("Got: " + expensiveOps.hashAllFiles(new File(".")) + "\n");

		log.debug("AAcum detectez ca s-a modificat un fisier in folder");
		// ce fac ?
		expensiveOps.killFolderCache(new File("."));

		log.debug("Folder MD5: ");
		log.debug("Got: " + expensiveOps.hashAllFiles(new File(".")) + "\n");
	}
	
}

@Retention(RetentionPolicy.RUNTIME)
@interface LoggedClass {}
@Retention(RetentionPolicy.RUNTIME)
@interface LoggedMethod {}

@Component
@Aspect
@Slf4j
@Order(1)
class LoggingInterceptor {
//	@Around("execution(* victor.training..*.*(..))") // aplicat aspectul pe toate clasele din pachetul X - periculos. tinde sa de nervi
//	@Around("execution(* *(..)) && @within(victor.training.oo.structural.proxy.LoggedClass)") // adnotari pe clase
	@Around("execution(* *(..)) && @annotation(victor.training.oo.structural.proxy.LoggedMethod)") // adnotari pe clase
	public Object logCall(ProceedingJoinPoint pjp) throws Throwable {
		log.debug("Calling method {} with params {}",
			pjp.getSignature().getName(),
			Arrays.toString(pjp.getArgs())
			);
		Object result = pjp.proceed();
		return result;
	}
}