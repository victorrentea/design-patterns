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

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
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
//		System.out.println("Oare cu cine vorbesc ?! "+ ops.getClass());
		log.debug("\n");

		ops.metodaInocenta();
		log.debug("---- CPU Intensive ~ memoization?");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");
		
		log.debug("---- I/O Intensive ~ \"There are only two things hard in programming...\"");
		log.debug("Folder MD5: ");
		log.debug("Got: " + ops.hashAllFiles(new File(".")) + "\n");
		log.debug("Got: " + ops.hashAllFiles(new File(".")) + "\n");

		log.debug("Am gasit o schimbare in folder");
		ops.aruncaCache(new File("."));

		log.debug("Folder MD5: ");
		log.debug("Got: " + ops.hashAllFiles(new File(".")) + "\n");
	}
	
}

@Retention(RetentionPolicy.RUNTIME)
@interface LoggedMethod {}

@Retention(RetentionPolicy.RUNTIME)
@interface LoggedClass {}
@Slf4j
@Aspect
@Component
class LoggingInterceptor {

//	@Around("execution(* victor..*.*(..))")
//	@Around("execution(* *(..)) && @annotation(victor.training.oo.structural.proxy.LoggedMethod)")
	@Around("execution(* *(..)) && @within(victor.training.oo.structural.proxy.LoggedClass)")
	public Object logheaza(ProceedingJoinPoint point) throws Throwable {
		log.debug("SRI: Invoca fraeru metoda {} cu parametrii {}",
				point.getSignature().getName(),
				Arrays.toString(point.getArgs()));
		return point.proceed();
		//
	}
}
