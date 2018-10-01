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
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.SneakyThrows;

@Service // SOLUTION
//public class ExpensiveOps { // INITIAL
public class ExpensiveOps implements IExpensiveOps { // SOLUTION
	
	private BigDecimal TWO = new BigDecimal("2");
	
	@Cacheable("expensive")
	public boolean isPrime(int n) { 
		BigDecimal number = new BigDecimal(n);
		if (number.compareTo(TWO) <= 0) {
			return true;
		}
		if (number.remainder(TWO).equals(BigDecimal.ZERO)) {
			return false;
		}
		for (BigDecimal divisor = new BigDecimal("3"); 
			divisor.compareTo(number.divide(new BigDecimal("2"))) < 0;
			divisor = divisor.add(TWO)) {
			if (number.remainder(divisor).equals(BigDecimal.ZERO)) {
				return false;
			}
		}
		return true;
	}

	@SneakyThrows
	@Cacheable("expensive")
	public String hashAllProjectFiles(File folder) {
		MessageDigest md = MessageDigest.getInstance("MD5");
		for (int i = 0; i< 3; i++) { // pretend there is more work to do here
			Files.walk(folder.toPath())
				.map(Path::toFile)
				.filter(File::isFile)
				.map(Unchecked.function(FileUtils::readFileToString))
				.forEach(s -> md.update(s.getBytes()));
		}
		byte[] digest = md.digest();
	    return DatatypeConverter.printHexBinary(digest).toUpperCase();
	}
	
}
