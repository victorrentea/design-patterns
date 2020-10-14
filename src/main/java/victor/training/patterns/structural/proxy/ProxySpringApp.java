package victor.training.patterns.structural.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;

@Slf4j
public class ProxySpringApp implements CommandLineRunner {
	public static void main(String[] args) throws Exception {
		new ProxySpringApp().run();
	}

	 
	// TODO [1] implement decorator 
	// TODO [2] apply decorator via Spring
	// TODO [3] generic java.lang.reflect.Proxy 
	// TODO [4] Spring aspect 
	// TODO [5] Spring cache support
	// TODO [6] Back to singleton (are you still alive?)
	public void run(String... args) throws Exception {
		IExpensiveOps ops = new ExpensiveOps(); 
		ops = new ExpensiveOpsWithCache(ops);
		bizMethod(ops);
		
		Writer w = new FileWriter("a.txt");
		w = new BufferedWriter(w); // decorater in JDK
			
		
	}


	private void bizMethod(IExpensiveOps ops) {
		log.debug("\n");
		log.debug("---- CPU Intensive ~ memoization?");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");
	}
	
}