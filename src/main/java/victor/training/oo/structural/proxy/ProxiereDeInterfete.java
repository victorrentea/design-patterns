package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxiereDeInterfete {

   public static void main(String[] args) {

      MateImpl mateImpl = new MateImpl();
      InvocationHandler callHandler = new InvocationHandler() {
         @Override
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Trec prin proxy catre metoda " + method.getName() + " cu param " + Arrays.toString(args));
            return method.invoke(mateImpl, args);
         }
      };

      Mate mate = (Mate) Proxy.newProxyInstance(ProxiereDeInterfete.class.getClassLoader(),
          new Class<?>[]{Mate.class}, callHandler);

      m(mate);
   }

   private static void m(Mate mate) {
      System.out.println(mate.suma(1, 1));
      System.out.println(mate.suma(2, 0));
      System.out.println(mate.suma(3, -1));
      System.out.println(mate.produs(-2, -1));

      System.out.println(mate.suma(3, 1));
   }

}

interface Mate {
   int suma(int a, int b);
   int produs(int a, int b);
}

class MateImpl implements Mate {
   @Override
   public int suma(int a, int b) {
      System.out.println("In metoda reala!");
      return a+b;
   }

   @Override
   public int produs(int a, int b) {
      return a*b;
   }
}
