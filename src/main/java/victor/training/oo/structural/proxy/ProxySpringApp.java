package victor.training.oo.structural.proxy;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableAspectJAutoProxy // SOLUTION
@EnableCaching // SOLUTION
@SpringBootApplication
public class ProxySpringApp implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(ProxySpringApp.class, args);
	}

	@Autowired private IExpensiveOps ops; // SOLUTION
	
	// TODO [1] implement decorator [2] generic proxy [3] Spring aspect [4] Spring cache support
	public void run(String... args) throws Exception {
//		ExpensiveOps ops = new ExpensiveOps(); // INITIAL
//		IExpensiveOps ops = new ExpensiveOpsCachingDecorator(new ExpensiveOps()); // SOLUTION
		
//		IExpensiveOps realOps = new ExpensiveOps();
//		IExpensiveOps ops = (IExpensiveOps) Proxy.newProxyInstance(ProxySpringApp.class.getClassLoader(), new Class<?>[] {IExpensiveOps.class}, new InvocationHandler() {
//			private Map<List<?>, Object> cache = new HashMap<>(); 
//			private List<Object> getCacheKey(String methodName, Object... args) {
//				List<Object> list = new ArrayList<>();
//				list.add(methodName);
//				list.addAll(asList(args));
//				return list;
//			}
//			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//				return cache.computeIfAbsent(getCacheKey(method.getName(), args),
//						Unchecked.function(k -> method.invoke(realOps, args)));
//			}
//		}); 
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
}