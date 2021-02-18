package victor.training.patterns.structural.proxy;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class ExpensiveOps {

   private final Map<Integer, Boolean> cache = new HashMap<>();

   private static final BigDecimal TWO = new BigDecimal("2");

   public Boolean isPrimeCached(int n) {
      if (cache.containsKey(n)) {
         return cache.get(n);
      }
      Boolean result = isPrime(n);
      cache.put(n, result);
      return result;
   }

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
