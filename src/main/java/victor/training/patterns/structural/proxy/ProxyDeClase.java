package victor.training.patterns.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ProxyDeClase {

   public static void main(String[] args) {

      MatematicaImpl impl = new MatematicaImpl();

      Callback handler = new InvocationHandler() {
         @Override
         public Object invoke(Object o, Method method, Object[] args) throws Throwable {
            System.out.println("Invoc metoda " + method.getName() + " cu param " + Arrays.toString(args));
//            if (am in cache) return din cache
//            if (n-are dreptul ) thow
            return method.invoke(impl, args); // TODO chem metoda reala
         }
      };
      MatematicaImpl proxyLaMatematica = (MatematicaImpl) Enhancer.create(MatematicaImpl.class, handler);

      cumArata(proxyLaMatematica);
   }

   private static void cumArata(MatematicaImpl m) {
      System.out.println(m.getClass());
      System.out.println(m.suma(1, 1));
      System.out.println(m.suma(2, 0));
      System.out.println(m.suma(3, -1));
      System.out.println(m.produs(2, 1));

      System.out.println(m.produs(2, 2));
   }

   static class MatematicaImpl {
      public int suma(int a, int b) {
         return a + b;
      }

      public int produs(int a, int b) {
         return a * b;
      }
   }

}

