package victor.training.patterns.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class MagiaStimuleazaCreativitatea {


   public static void main(String[] args) {

      MatematicaImpl real = new MatematicaImpl();

      InvocationHandler h = new InvocationHandler() {
         @Override
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Chem metoda " + method.getName() + " cu param " + Arrays.toString(args));
            return method.invoke(real, args);
         }
      };

      Matematica mateProxy = (Matematica) Proxy.newProxyInstance(MagiaStimuleazaCreativitatea.class.getClassLoader(),
          new Class<?>[]{Matematica.class}, h);

//      demo(new MatematicaImpl());
      demo(mateProxy);
   }

   private static void demo(Matematica mate) {
      System.out.println("Am primit un param de tip: " + mate.getClass());
      if (mate instanceof Matematica) {
         System.out.println("TOT TIMPUL");
      }
      if (mate instanceof MatematicaImpl) {
         System.out.println("Real impl");
      }
      System.out.println(mate.suma(1, 1));
      System.out.println(mate.suma(2, 0));
      System.out.println(mate.suma(3, -1));
      System.out.println(mate.suma(3, 1));
      System.out.println(mate.produs(3, 2));

   }
}

class MatematicaImpl implements Matematica {

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
