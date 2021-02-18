package victor.training.patterns.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

interface Mate {
   int sum(int a, int b);

   int produs(int a, int b);
}

public class Magie {
   public static void main(String[] args) {

      MateImpl real = new MateImpl();

      InvocationHandler h = new InvocationHandler() {
         @Override
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("calling  " + method.getName() + " with " + Arrays.toString(args));
            return method.invoke(real, args);
         }
      };


      Mate mate = (Mate) Proxy.newProxyInstance(Magie.class.getClassLoader(),
          new Class<?>[]{Mate.class},
          h);

      System.out.println(mate.sum(1, 1));
      System.out.println(mate.produs(1, 2));
      System.out.println(mate.sum(0, 2));
      System.out.println(mate.sum(-1, 3));
      System.out.println(mate.sum(1, 3));
   }
}


class MateImpl implements Mate {

   @Override
   public int sum(int a, int b) {
      return a + b;
   }

   @Override
   public int produs(int a, int b) {
      return a * b;
   }
}
