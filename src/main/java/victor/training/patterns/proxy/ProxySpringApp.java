package victor.training.patterns.proxy;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Arrays;

@Slf4j
@EnableCaching//(order = )
@SpringBootApplication
public class ProxySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ProxySpringApp.class, args);
	}


	@Autowired
	ExpensiveOps ops; // = tzeapa (proxy)
	// daca spring are nevoie sa INTERCEPTEZE vreo metoda din clasa injectata
	
	// TODO [1] implement decorator 
	// TODO [2] apply decorator via Spring
	// TODO [3] generic java.lang.reflect.Proxy 
	// TODO [4] Spring aspect 
	// TODO [5] Spring cache support
	// TODO [6] Back to singleton (are you still alive?)
	public void run(String... args) {
		log.debug("si eu credeam in Spring: " + ops.getClass());
		log.debug("Oare ce mi-a inejctat Springu cand io i-am cerut un ExpensiveOps? " + ops.getClass());
		log.debug("\n");
		log.debug("---- CPU Intensive ~ memoization?");
		log.debug("10000169 is prime ? ");

		TransactionTemplate txTemplate = new TransactionTemplate();
		txTemplate.setPropagationBehaviorName("REQUIRES_NEW");
		txTemplate.execute(s -> ops.isPrime(10000169) );
		log.debug("Got: " + FP.log(() -> ops.isPrime(10000169)) + "\n");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");
		
	}
}

@Component
@Aspect
class Interceptor {
	@SneakyThrows
	@Around("execution(* victor.training.patterns.proxy.ExpensiveOps.*(..))")
	public Object method(ProceedingJoinPoint pjp) {
		System.out.println("Calling " + pjp.getSignature().getName() + " cu param " + Arrays.toString(pjp.getArgs()));
		return pjp.proceed(); // cheam-o p-aia reala
	}
}