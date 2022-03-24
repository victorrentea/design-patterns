package victor.training.patterns.structural.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public class ExpensiveOps {

   private static final BigDecimal TWO = new BigDecimal("2");

   @Cacheable("primesXArbt")
   public Boolean isPrime(int n) {
//      new RuntimeException("intentional").printStackTrace();
      log.debug("Computing isPrime({}) TAKES TIME", n);
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


   public String trap() {
      System.out.println("A local method call does NOT go through the proxy > " +
                         "annotaitons don't work: @PreAuthorized, @Transactional, @Cacheable");
      return isPrime(10000169) + " by now the cache CONTAINS this number";
   }
}
