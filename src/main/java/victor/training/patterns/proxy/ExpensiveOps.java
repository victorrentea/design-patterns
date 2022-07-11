package victor.training.patterns.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Component
public class ExpensiveOps {
   private static final Logger log = LoggerFactory.getLogger(ExpensiveOps.class);

   private static final BigDecimal TWO = new BigDecimal("2");

   @Cacheable("primes")
//   @Transactional
//   @PreAuthorized
//   @Secured
//   @Timed
//   @Retryable()
//   @Async
   public Boolean isPrime(int n) {
      if(true)throw new IllegalArgumentException();
      log.debug("Computing isPrime({}) - EXPENSIVE", n);
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
