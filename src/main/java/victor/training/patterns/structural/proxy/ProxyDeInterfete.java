package victor.training.patterns.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyDeInterfete {

   public static void main(String[] args) {

      MatematicaImpl impl = new MatematicaImpl();

      InvocationHandler handler = new InvocationHandler() {
         @Override
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Invoc metoda " + method.getName() + " cu param " + Arrays.toString(args));
            return method.invoke(impl, args); // TODO chem metoda reala
         }
      };
      Matematica proxyLaMatematica = (Matematica) Proxy.newProxyInstance(
          ProxyDeInterfete.class.getClassLoader(),
          new Class<?>[]{Matematica.class},
          handler
      );

      codulClient(proxyLaMatematica);
   }

   private static void codulClient(Matematica m) {
      System.out.println(m.suma(1, 1));
      System.out.println(m.suma(2, 0));
      System.out.println(m.suma(3, -1));
      System.out.println(m.produs(2, 1));

      System.out.println(m.produs(2, 2));
   }

   static class MatematicaImpl implements Matematica {
      @Override
      public int suma(int a, int b) {
         return a + b;
      }

      @Override
      public int produs(int a, int b) {
         return a * b;
      }
   }

   interface Matematica {
      int suma(int a, int b);

      int produs(int a, int b);
   }
}

