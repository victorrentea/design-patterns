package victor.training.oo.structural.proxy;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.jooq.lambda.Unchecked;
import org.springframework.util.StopWatch;

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
   String hashAllFiles(File folder);
}

//Decorator
class ExpensiveOpsWithCache implements IExpensiveOps{
   private Map<Integer, Boolean> cache = new HashMap<>();
   private final IExpensiveOps expensiveOps;

   public ExpensiveOpsWithCache(IExpensiveOps expensiveOps) {
      this.expensiveOps = expensiveOps;
   }
   public Boolean isPrime(int n) {
      if (cache.containsKey(n)) {
         return cache.get(n);
      }
      Boolean result = expensiveOps.isPrime(n);
      cache.put(n, result);
      return result;
   }

   @Override
   public String hashAllFiles(File folder) {
      StopWatch stopWatch = new StopWatch();
      stopWatch.start();
      try {
         return expensiveOps.hashAllFiles(folder);
      } finally {
         stopWatch.stop();
         long dt = stopWatch.getTotalTimeMillis();
         System.out.println("Took " + dt + " millis");
      }
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


   @Override
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
      return DatatypeConverter.printHexBinary(digest).toUpperCase();
   }

}
