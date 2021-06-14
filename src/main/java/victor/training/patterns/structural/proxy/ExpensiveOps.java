package victor.training.patterns.structural.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;

@Slf4j
@Service
public /*final*/ class ExpensiveOps {

   private static final BigDecimal TWO = new BigDecimal("2");

   //   @Transactional
   @Cacheable("primes")
//   @Async
//   @PreAuthorized / @RolesAllowed / @Secured
//   @Retryable
   public Boolean isPrime(@Validated int n) {
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


   public void someOtherMethod() {

      log.debug("10000169 is prime ? ");
      log.debug("Got: " + isPrime(10000169) + "\n"); // local method calls are NOT proxied ! PANIC!
   }
}
