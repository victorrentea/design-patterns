package victor.training.oo.structural.proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProxySpringApp{
	public static void main(String[] args) {

		ExpensiveOpsCached ops = new ExpensiveOpsCached(new ExpensiveOps());

		functieDeBiznis(ops);

	}

	private static void functieDeBiznis(IExpensiveOps ops) {
		log.debug("\n");
		log.debug("---- CPU Intensive ~ memoization?");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10_000_169) + "\n");
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + ops.isPrime(10_000_169) + "\n");
	}

}