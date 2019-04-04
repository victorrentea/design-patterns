package victor.training.oo.structural.proxy;

import static java.util.Arrays.asList;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import lombok.extern.slf4j.Slf4j;

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
		IExpensiveOps ops = new ExpensiveOps();
		holySacredBusinessLogic(new ExpensiveOpsWithCache(ops));
	}

	private void holySacredBusinessLogic(IExpensiveOps ops) {
		log.debug("\n");
		log.debug("---- CPU Intensive ~ memoization?");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10000169) + "\n");

		log.debug("---- I/O Intensive ~ \"There are only two things hard in programming...\"");
		log.debug("Folder MD5: ");
		log.debug("Got: " + ops.hashAllFiles(new File(".")) + "\n");
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



class ExpensiveOpsWithCache implements IExpensiveOps {
	private final IExpensiveOps delegate;

	public ExpensiveOpsWithCache(IExpensiveOps delegate) {
		this.delegate = delegate;
	}

	private static final Map<Integer, Boolean> primesCache = new HashMap<>();
	private static final Map<File, String> foldersCache = new HashMap<>();

	public Boolean isPrime(int n) {
		return primesCache.computeIfAbsent(n, delegate::isPrime);
	}

	public String hashAllFiles(File folder) {
		return foldersCache.computeIfAbsent(folder, delegate::hashAllFiles);
	}

}