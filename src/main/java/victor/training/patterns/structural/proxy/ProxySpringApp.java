package victor.training.patterns.structural.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@EnableCaching
@SpringBootApplication
public class ProxySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ProxySpringApp.class, args);
	}

	@Autowired
	private ExpensiveOps ops;
	//whenver you take an object from Spring you might get not your class but
	// a DYNAMIC SUBCLASS OF YOUR TIME GENERATED AT RUNTIME USING ENHANCER (CGLIB lib)

//	= new ExpensiveOps() {
//		@Override
//		public Boolean isPrime(int n) {
//			return from cache first, and if not allow the call go pass through.
//		}
//	};

	// TODO [1] implement decorator 
	// TODO [2] apply decorator via Spring
	// TODO [3] generic java.lang.reflect.Proxy 
	// TODO [4] Spring aspect 
	// TODO [5] Spring cache support
	// TODO [6] Back to singleton (are you still alive?)
	public void run(String... args) throws Exception {
//		ExpensiveOps ops = new ExpensiveOps();
		log.debug("WHO AM I TALKING TO ?" + ops.getClass());


		log.debug("\n");
		log.debug("---- CPU Intensive ~ memoization?");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");
	}
	
}