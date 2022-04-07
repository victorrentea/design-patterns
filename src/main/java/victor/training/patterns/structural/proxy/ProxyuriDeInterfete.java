package victor.training.patterns.structural.proxy;

import lombok.RequiredArgsConstructor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class ProxyuriDeInterfete {

   public static void main(String[] args) {
      // springu sub capota
      MatematicaImpl impl = new MatematicaImpl();

      InvocationHandler callHandler = new InvocationHandler() {
         @Override
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Cheama " + method.getName() + " + cu arg " + Arrays.toString(args));
            return method.invoke(impl, args);
         }
      };
      // JDK proxies
      Matematica proxyLaMate = (Matematica) Proxy.newProxyInstance(ProxyuriDeInterfete.class.getClassLoader(),
          new Class<?>[]{Matematica.class},
          callHandler);

      Clasa2 clasa2 = new Clasa2(proxyLaMate);
      clasa2.oraDeMate();
   }

   //   @Service
   @RequiredArgsConstructor
   static class Clasa2 {
      private final Matematica matematica;

      public void oraDeMate() {
         System.out.println(matematica.impartiri(12, 4));
         System.out.println(matematica.inmultire(3, 4));
      }
   }

   interface Matematica {
      int impartiri(int impartit, int impartitor);

      int inmultire(int a, int b);
   }

   //   @Service
   static class MatematicaImpl implements Matematica {
      public int impartiri(int impartit, int impartitor) {
         return impartit / impartitor;
      }

      public int inmultire(int a, int b) {
         return a * b;
      }
   }
}
