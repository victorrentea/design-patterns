package victor.training.patterns.structural.proxy;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.jooq.lambda.Unchecked;

import javax.xml.bind.DatatypeConverter;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

interface IExpensiveOps {
	Boolean isPrime(int n);
}

// DECORATOR PATTERN
class ExpensiveOpsWithCache implements IExpensiveOps{
	private final IExpensiveOps delegate;
	
	
	public ExpensiveOpsWithCache(IExpensiveOps delegate) {
		this.delegate = delegate;
	}

	private Map<Integer, Boolean> cache = new HashMap<>(); 
	
	public Boolean isPrime(int n) {
		if (cache.containsKey(n)) {
			return cache.get(n);
		} 
		Boolean result = delegate.isPrime(n);
		cache.put(n, result);
		return result;
	}
}

@Slf4j
public class ExpensiveOps implements IExpensiveOps{
	private static final BigDecimal TWO = new BigDecimal("2");
	
	
	public Boolean isPrime(int n) {
		log.debug("Computing isPrime({})", n);
		BigDecimal number = new BigDecimal(n);
		if (number.compareTo(TWO) <= 0) {
			return true;
		}
		if (number.remainder(TWO).equals(BigDecimal.ZERO)) {
			return false;
		}
		for (BigDecimal divisor = new BigDecimal("3"); 
			divisor.compareTo(number.divide(TWO)) < 0;
			divisor = divisor.add(TWO)) {
			if (number.remainder(divisor).equals(BigDecimal.ZERO)) {
				return false;
			}
		}
		return true;
	}

	
}
