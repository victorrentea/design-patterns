package victor.training.patterns.behavioral.template;

import victor.training.patterns.stuff.ThreadUtils;

import java.util.function.Supplier;

public class ManualAOP {

   public static void main(String[] args) {


      int r = measure(() -> method(2));

      System.out.println(r);

   }

   private static <T> T measure(Supplier<T> callable) {
      long t0 = System.currentTimeMillis();
      T r = callable.get();
      long t1 = System.currentTimeMillis();
      System.out.println("Took " + (t1 - t0));
      return r;
   }

   public static int method(int a) {
      // chestii
      ThreadUtils.sleepq(1000);
      return a + 1;
   }
}
