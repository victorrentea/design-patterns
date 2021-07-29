package victor.training.patterns.structural.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Slf4j
class ExpensiveOpsCuCache implements ExpensiveOps {
   private final ExpensiveOps expensiveOps;
   private Map<Integer, Boolean> cache = new HashMap<>();

   public ExpensiveOpsCuCache(ExpensiveOps expensiveOps) {
      this.expensiveOps = expensiveOps;
   }

   public Boolean isPrime(int n) {
      if (cache.containsKey(n)) {
         return cache.get(n);
      }
      Boolean prime = expensiveOps.isPrime(n);
      cache.put(n, prime);
      return prime;
   }
}

@Slf4j
@Service
class ExpensiveOpsImpl/* implements ExpensiveOps */ {
   private static final BigDecimal TWO = new BigDecimal("2");

   @Cacheable("primes")
//   @Transactional
//   @PreAuthorized
//   @Async
//   @Retryable
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

   public void altaMetoda() {
      new RuntimeException().printStackTrace();

   }


}

interface ExpensiveOps {
   Boolean isPrime(int n);
}
