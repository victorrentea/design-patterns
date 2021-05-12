package victor.training.patterns.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class MagiaStimuleazaCreativitatea {

//   @Autowired
//   Matematica matematica;

   public static void main(String[] args) {

      Matematica real = new Matematica();

      Callback callback = new MethodInterceptor() {
         @Override
         public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            System.out.println("Chem metoda " + method.getName() + " cu param " + Arrays.toString(args));
            return method.invoke(real, args);
         }
      };
      Matematica mateProxy = (Matematica) Enhancer.create(Matematica.class, callback);

//      demo(new MatematicaImpl());
      demo(mateProxy);
   }

   private static void demo(Matematica mate) {
      System.out.println("Am primit un param de tip: " + mate.getClass());
      System.out.println(mate.suma(1, 1));
      System.out.println(mate.suma(2, 0));
      System.out.println(mate.suma(3, -1));
      System.out.println(mate.suma(3, 1));
      System.out.println(mate.produs(3, 2));

   }
}

class Matematica {

   public int suma(int a, int b) {
      return a + b;
   }

   public int produs(int a, int b) {
      return a * b;
   }
}

