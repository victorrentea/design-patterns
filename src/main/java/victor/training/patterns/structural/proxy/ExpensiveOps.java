package victor.training.patterns.structural.proxy;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.io.FileUtils;
import org.jooq.lambda.Unchecked;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.MessageDigest;

@Slf4j
@Component
public /*final*/ class ExpensiveOps {

   private static final BigDecimal TWO = new BigDecimal("2");

   public Boolean anotherMethodInTheSameClass(int n) {
      return isPrime(n);
   }

   //   @Transactional
//   @PreAuthorized
//   @Retryable
//   @RolesAllowed
//   @Async
//   @Timed
//   @
   @Autowired
   CacheManager cacheManager;

   @Cacheable("primes")
//   @Transactional(propagation =  Propagation.REQUIRES_NEW)
   public /*final ingores THIS method*/ Boolean isPrime(int n) {

//      Object cachedValue = cacheManager.getCache("primes").get(n).get(); // there is always a programmatic alternaitve alternative to Spring annotation

      log.debug("Computing isPrime({})", n);
      BigDecimal number = new BigDecimal(n);
      if (number.compareTo(TWO) <= 0) {
         return true;
      }
//      if(true)
//      throw new IllegalArgumentException();
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

   @SneakyThrows
   public String hashAllFiles(File folder) {
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
      return Hex.encodeHexString(digest).toUpperCase();
   }

}
