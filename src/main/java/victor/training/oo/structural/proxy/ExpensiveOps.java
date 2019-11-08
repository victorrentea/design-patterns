package victor.training.oo.structural.proxy;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.io.FileUtils;
import org.jooq.lambda.Unchecked;
import org.springframework.aop.config.AopConfigUtils;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
//@LoggedClass
public class ExpensiveOps {
	
	private static final BigDecimal TWO = new BigDecimal("2");

	@Cacheable("primes")
	@LoggedMethod
//	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Boolean isPrime(int n) {
		new RuntimeException().printStackTrace();
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


	@Cacheable("foldere")
	@SneakyThrows
	public String hashAllFiles(File folder) {


//		CacheManager // programatic cache management
		log.debug("10000169 is prime ? ");
		ExpensiveOps myselfProxied = (ExpensiveOps) AopContext.currentProxy();
		log.debug("Got: " + myselfProxied.isPrime(10_000_169) + "\n");

		log.debug("Computing hashAllFiles({})", folder);
		MessageDigest md = MessageDigest.getInstance("MD5");
		for (int i = 0; i < 3; i++) { // pretend there is much more work to do here
			Files.walk(folder.toPath())
				.map(Path::toFile)
				.filter(File::isFile)
				.map(Unchecked.function(FileUtils::readFileToString))
				.forEach(s -> md.update(s.getBytes()));
		}
		byte[] digest = md.digest();
	    return DatatypeConverter.printHexBinary(digest).toUpperCase();
	}

	@CacheEvict("foldere")
	public void omorCacheul(File file) {
		// EMPTY METHOD. DO NOT TOUCH. ET THE MAGIC HAPPEN
	}
}
