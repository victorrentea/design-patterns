package victor.training.patterns.structural.proxy;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

interface IExpensiveOps {
   Boolean isPrime(int n);
}

class ExpensiveOpsCuCache implements IExpensiveOps {
   private final IExpensiveOps ops;
   private final Map<Integer, Boolean> cache = new HashMap<>();

   public ExpensiveOpsCuCache(IExpensiveOps ops) {
      this.ops = ops;
   }

   public Boolean isPrime(int n) {
      if (cache.containsKey(n)) {
         return cache.get(n);
      }
      Boolean result = ops.isPrime(n);
      cache.put(n, result);
      return result;
   }
}

@Slf4j
public class ExpensiveOps implements IExpensiveOps {
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


}
