package victor.training.oo.structural.proxy;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.DatatypeConverter;

import com.sun.istack.internal.NotNull;
import lombok.extern.java.Log;
import org.apache.commons.io.FileUtils;
import org.jooq.lambda.Unchecked;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Slf4j
@Service
//@LoggedClass
public class ExpensiveOps /*implements IExpensiveOps*/ {
	
	private static final BigDecimal TWO = new BigDecimal("2");

	@LoggedMethod
	@Cacheable("primesX")
	public Boolean isPrime(int n) {
		log.debug("Computing isPrime({})", n);
//		new RuntimeException().printStackTrace();
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
	@Cacheable("folders")
	@SneakyThrows
	@Validated
	public String hashAllFiles(@NotNull  File folder) {
		log.debug("Computing hashAllFiles({})", folder);
		log.debug("10000169 is prime ? ");
		log.debug("Got: " + isPrime(10000169) + "\n");

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

	@CacheEvict("folders")
	public void killCache(File file) {
		// DO NOT TOUCH: Let the magic happen
	}
}
