package victor.training.patterns.structural.proxy;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;


@Slf4j
class Scena1_nrMici {
//   public static void main(String[] args) {
//      System.out.println(new ExpensiveOps().isPrime(107));
//      log.debug("is prime : " + new ExpensiveOps().isPrime(10_000_169));
//   }
}

class ObiectImutabil {
   private final String name;
   private final List<String> phones;

   ObiectImutabil(String name, List<String> phones) {
      this.name = name;
      this.phones = phones;
   }

   public List<String> getPhones() {
//      return new ArrayList<>(phones); //waste of mem
//      return new UnmodifiableList<>(phones); // cupleaza codul tau la clasa concreta construita
      return Collections.unmodifiableList(phones); // static factory method returning a decorator
   }

   public String getName() {
      return name;
   }

   @Override
   public String toString() {
      return "ObiectImutabil{" + "name='" + name + '\'' + ", phones=" + phones + '}';
   }
}

@Slf4j
class Scena1_nrMari {
   public static void main(String[] args) {
      ObiectImutabil obj = new ObiectImutabil("nume", new ArrayList<>());
      System.out.println(obj);

      obj.getPhones().add("las si io aicea ca e tarziu...");

      System.out.println(obj);

      codClient(new ExpensiveOpsImpl());
      codClient(new ExpensiveOpsCuCache(new ExpensiveOpsImpl()));
   }

   private static void codClient(ExpensiveOps una) {
      log.debug("is prime : " + una.isPrime(10_000_169));

      log.debug("is prime : " + una.isPrime(10_000_169));
   }
}

// Decorator Pattern
class ExpensiveOpsCuCache implements ExpensiveOps {
   private final ExpensiveOps delegate;

   // DOAMNE FERESTE SA PUI ASTA-N PROD
   private final Map<Integer, Boolean> cacheLaTara = new HashMap<>();

   ExpensiveOpsCuCache(ExpensiveOps delegate) {
      this.delegate = delegate;
   }

   public Boolean isPrime(int n) {
      if (cacheLaTara.containsKey(n)) {
         return cacheLaTara.get(n);
      }
      Boolean r = delegate.isPrime(n);
      cacheLaTara.put(n, r);
      return r;
   }
}

public interface ExpensiveOps {
   Boolean isPrime(int n);
}

class ExpensiveOpsImpl implements ExpensiveOps {
   private static final Logger log = LoggerFactory.getLogger(ExpensiveOps.class);

   private static final BigDecimal TWO = new BigDecimal("2");

   public Boolean isPrime(int n) {
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
