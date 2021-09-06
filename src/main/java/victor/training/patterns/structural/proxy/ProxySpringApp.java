package victor.training.patterns.structural.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import victor.training.patterns.creational.builder.Customer;

@Slf4j
@EnableCaching
@SpringBootApplication
public class ProxySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ProxySpringApp.class, args);
	}

	@Autowired
	ExpensiveOps ops;

	
	// TODO [1] implement decorator 
	// TODO [2] apply decorator via Spring
	// TODO [3] generic java.lang.reflect.Proxy 
	// TODO [4] Spring aspect 
	// TODO [5] Spring cache support
	// TODO [6] Back to singleton (are you still alive?)
	public void run(String... args) throws Exception {
//		ExpensiveOps ops = new ExpensiveOps();
		System.out.println("Who am I talking to? " + ops.getClass());
		log.debug("\n");
		log.debug("---- CPU Intensive ~ memoization?");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n"); // expensive computation
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");

		IChicken chicken = new Chicken();
		IChicken screamingChicken = new ScreamingChicken(chicken);
		// farmer
		screamingChicken.layEgg();

//		InputStream fileInputStream = new FileInputStream("a.txt");
//		InputStream bis = new BufferedInputStream(fileInputStream);

		Customer customer = new Customer();
		customer.getLabels().clear();

	}
}

class ScreamingChicken implements IChicken {
	private final IChicken delegate;

	ScreamingChicken(IChicken delegate) {
		this.delegate = delegate;
	}

	@Override
	public void layEgg() {
		delegate.layEgg();
		System.out.println("Quacaac! Bragging about the egg.");
	}
}

class Chicken implements IChicken {
	public void layEgg() {

	}
}

// Decorator Pattern?
interface IChicken {
	void layEgg();
}