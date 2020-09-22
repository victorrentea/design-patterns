package victor.training.oo.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Magie {
   public static void main(String[] args) {

      Mate realImplem = new Mate();

//      IMate mate = (IMate) Proxy.newProxyInstance(Magie.class.getClassLoader(),
//          new Class<?>[]{IMate.class}, h);

      Callback callback = new org.springframework.cglib.proxy.InvocationHandler() {
         @Override
         public Object invoke(Object o, Method method, Object[] args) throws Throwable {
            System.out.println("Calling " + method.getName() + " cu param " + Arrays.toString(args));
            return method.invoke(realImplem, args);
         }
      };
      Mate mate = (Mate) Enhancer.create(Mate.class, callback);
      System.out.println(mate.getClass());

      metodaCareDepindeMate(mate);
   }

   private static void metodaCareDepindeMate(Mate mate) {
      System.out.println(mate.suma(1, 1));
      System.out.println(mate.suma(2, 0));
      System.out.println(mate.suma(3, -1));
      System.out.println(mate.suma(3, 1));
      System.out.println(mate.produs(2, 1));
   }
}

/*final */class Mate {

   public /*final*/ int suma(int a, int b) {
      return a+b;
   }

   public int produs(int a, int b) {
      return a*b;
   }
}