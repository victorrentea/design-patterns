package victor.training.patterns.structural.proxy;

import lombok.extern.slf4j.Slf4j;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

@Slf4j
public class DecoratorPlay {

   public static void main(String[] args) throws IOException {

      Writer w = new FileWriter("a.txt");
//      w = new BufferedWriter(w);

      f(new ExpensiveOpsCuCache(new ExpensiveOps()));
//      f(new AltDecorator(new ExpensiveOpsCuCache(new ExpensiveOps())));
      f(new ExpensiveOps());

   }

   private static void f(IExpensiveOps ops) {
      log.debug("---- CPU Intensive ~ memoization?");
      log.debug("10000169 is prime ? ");
      log.debug("Got: " + ops.isPrime(10000169) + "\n");
      log.debug("10000169 is prime ? ");
      log.debug("Got: " + ops.isPrime(10000169) + "\n");
   }
}


