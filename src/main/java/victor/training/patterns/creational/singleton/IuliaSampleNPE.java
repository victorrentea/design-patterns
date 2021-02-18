package victor.training.patterns.creational.singleton;

import static java.util.Objects.requireNonNull;

public class IuliaSampleNPE {
   public Integer method() {
      return f() + 1;
   }

   private Integer f() {
      return requireNonNull(g());
   }

   private Integer g() {
      Integer x = requireNonNull(null);
      // if (x=)
      return x;
   }
}


