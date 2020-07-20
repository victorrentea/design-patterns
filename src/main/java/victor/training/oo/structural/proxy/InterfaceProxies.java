package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class InterfaceProxies {
   public static void main(String[] args) {

      MateImpl mateImpl = new MateImpl();

      InvocationHandler h = new InvocationHandler() {
         @Override
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Cheama metoda " + method.getName() + "(" + Arrays.toString(args) + ")");
            return method.invoke(mateImpl, args);
         }
      };
      Mate mate = (Mate) Proxy.newProxyInstance(InterfaceProxies.class.getClassLoader(),
          new Class<?>[]{Mate.class},
          h);


      System.out.println(mate.suma(1,1));
      System.out.println(mate.suma(2,0));
      System.out.println(mate.suma(3,-1));
      System.out.println(mate.produs(2,1));

      System.out.println(mate.suma(3,1));
   }
}

class MateImpl implements Mate{

   @Override
   public int suma(int a, int b) {
      return a + b;
   }

   @Override
   public int produs(int a, int b) {
      return a * b;
   }
}

interface Mate {
   int suma(int a, int b);
   int produs(int a, int b);
}
