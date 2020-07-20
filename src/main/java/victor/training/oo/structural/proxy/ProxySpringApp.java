package victor.training.oo.structural.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import java.io.File;

@Slf4j
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

	@Autowired
	private ExpensiveOpsImpl ops;


	public void run(String... args) throws Exception {

		metodaDeBizni$$(ops);
	}

	private void metodaDeBizni$$(ExpensiveOpsImpl ops) {

		log.debug("Oare cu cine vorbesc eu ? " + ops.getClass());
		log.debug("\n");
		log.debug("---- CPU Intensive ~ memoization?");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");

		log.debug("---- I/O Intensive ~ \"There are only two things hard in programming...\"");
		log.debug("Folder MD5: ");
		log.debug("Got1: " + ops.hashAllFiles(new File(".")) + "\n");
		log.debug("Got2: " + ops.hashAllFiles(new File(".")) + "\n");

		//aici gasesti ca un fis s-a schimbat
		ops.aruncaCacheulDeFoldere(new File("."));
		log.debug("Folder MD5: ");
		log.debug("Got3: " + ops.hashAllFiles(new File(".")) + "\n");
	}

}