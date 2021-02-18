package victor.training.patterns.structural.proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DecoratorPlay {

   public static void main(String[] args) {

      f(new ExpensiveOps());

   }

   private static void f(ExpensiveOps ops) {
      log.debug("---- CPU Intensive ~ memoization?");
      log.debug("10000169 is prime ? ");
      log.debug("Got: " + ops.isPrime(10000169) + "\n");
      log.debug("10000169 is prime ? ");
      log.debug("Got: " + ops.isPrime(10000169) + "\n");
   }
}


