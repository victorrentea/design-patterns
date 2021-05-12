package victor.training.patterns.structural.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

//interface IExpensiveOps {
//   Boolean isPrime(int n);
// }
//
// class ExpensiveOpsCuCache implements IExpensiveOps {
//   private final IExpensiveOps delegate;
//
//    ExpensiveOpsCuCache(IExpensiveOps delegate) {
//       this.delegate = delegate;
//    }
//   static private Map<Integer, Boolean> primes = new HashMap<>();
//
//    @Override
//    public Boolean isPrime(int n) {
//      if (primes.containsKey(n)) {
//         return primes.get(n);
//      }
//
//      Boolean prime = delegate.isPrime(n);
//      primes.put(n, prime);
//      return prime;
//   }
// }

@Slf4j
@Service
public class ExpensiveOps {

   private static final BigDecimal TWO = new BigDecimal("2");

   @Autowired
   private ExpensiveOps myselfProxied;

   @Cacheable("primes")
//   @Transactional(propagation = Propagation.REQUIRES_NEW)
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

   public void altaMetodaDinAceeasiClasa() {
      log.debug("10000169 is prime ? ");
      log.debug("Got: " + myselfProxied.isPrime(10000169) + "\n");
   }
}
