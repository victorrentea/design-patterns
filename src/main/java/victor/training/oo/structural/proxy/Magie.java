package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Magie {
   public static void main(String[] args) {

      Mate realImplem = new Mate();
      InvocationHandler h = new InvocationHandler() {
         @Override
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Calling " + method.getName() + " cu param " + Arrays.toString(args));
            return method.invoke(realImplem, args);
         }
      };

      IMate mate = (IMate) Proxy.newProxyInstance(Magie.class.getClassLoader(),
          new Class<?>[]{IMate.class}, h);
      System.out.println(mate.getClass());

      System.out.println(mate.suma(1, 1));
      System.out.println(mate.suma(2, 0));
      System.out.println(mate.suma(3, -1));
      System.out.println(mate.suma(3, 1));
      System.out.println(mate.produs(2, 1));
   }
}

interface IMate {
   int suma(int a, int b);
   int produs(int a, int b);
}
class Mate implements IMate {

   @Override
   public int suma(int a, int b) {
      return a+b;
   }

   @Override
   public int produs(int a, int b) {
      return a*b;
   }
}