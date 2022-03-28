package victor.training.patterns.structural.proxy;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Slf4j
@Service
public /*final*/ class ExpensiveOps {

   private static final BigDecimal TWO = new BigDecimal("2");

   @Autowired
   private CacheManager cacheManager;

   @Cacheable("primes")
   public /*final*/ Boolean isPrime(int n) {
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

   public void localCallInsideTheSameClass() {
      log.debug("10000169 is prime ?  here the cache already contains the number");
      // if you call a method in the same class, NO PROXIES can ever intercept your call
      log.debug("Got: " + isPrime(10000169) + "\n");
   }

}

@Service
class AnotherClass {
   private static final Logger log = LoggerFactory.getLogger(AnotherClass.class);
   @Autowired
   ExpensiveOps myselfProxied;

   public void localCallInsideTheSameClass() {
      log.debug("10000169 is prime ?  here the cache already contains the number");
      // if you call a method in the same class, NO PROXIES can ever intercept your call
      log.debug("Got: " + myselfProxied.isPrime(10000169) + "\n");
   }
}

//class CGLibGenerates extends ExpensiveOps {
//   @Override
//   public Boolean isPrime(int n) {
//      // stuff
//      return super.isPrime(n);
//   }
//}