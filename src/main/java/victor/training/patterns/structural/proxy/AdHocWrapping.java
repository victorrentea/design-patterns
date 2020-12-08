package victor.training.patterns.structural.proxy;

import victor.training.patterns.stuff.ThreadUtils;

public class AdHocWrapping {

   public static void main(String[] args) {

      long dt = measure(AdHocWrapping::method);

      System.out.println(dt);

   }

   private static long measure(Runnable o) {
      long t0 = System.currentTimeMillis();
      o.run();
      long t1 = System.currentTimeMillis();
      return t1-t0;
   }

   public static void method() {
       // face cehstii
      ThreadUtils.sleepq(100);
      // oara cat o dura treaba aici ?
   }
}
