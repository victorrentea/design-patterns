package victor.training.patterns.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class HaiSaMaJoc {
   public static void main(String[] args) {
      Mate realMate = new MateImpl();

      InvocationHandler h = new InvocationHandler() {
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("SRI: Se cheama metoda " + method.getName() + " " + Arrays.toString(args));
            return method.invoke(realMate, args);
         }
      };

      Mate mate = (Mate) Proxy.newProxyInstance(HaiSaMaJoc.class.getClassLoader(),
          new Class<?>[] {Mate.class}, h);

      coduClient(mate);
   }

   public static void coduClient(Mate mate) {
      System.out.println(mate.sum(1, 1));
      System.out.println(mate.sum(2, 0));
      System.out.println(mate.sum(3, -1));
      System.out.println(mate.sum(3, 1));
      System.out.println(mate.product(2, 2));
   }
}


interface Mate {
   int sum(int a, int b);
   int product(int a, int b);
}

class MateImpl implements Mate {
   public int sum(int a, int b) {
      return a + b;
   }

   @Override
   public int product(int a, int b) {
      return a * b;
   }
}

