package victor.training.oo.structural.proxy;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ProxySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ProxySpringApp.class, args);
	}

	@Autowired
	private IExpensiveOps ops;
	
	public void run(String... args) throws Exception {
//		IExpensiveOps ops = new ExpensiveOpsCachingDecorator(new ExpensiveOps()); // SOLUTION
		
		System.out.println("CPU Intensive ~ memoization?");
		System.out.print("10000169 is prime ? ");
		System.out.println(ops.isPrime(10000169));
		System.out.print("10000169 is prime ? ");
		System.out.println(ops.isPrime(10000169));
		System.out.println();
		
		System.out.println("I/O Intensive ~ \"There are only two things hard in programming...\"");
		System.out.print("Folder MD5: ");
		System.out.println(ops.hashAllProjectFiles(new File(".")));
		System.out.print("Folder MD5: ");
		System.out.println(ops.hashAllProjectFiles(new File(".")));
	}
}