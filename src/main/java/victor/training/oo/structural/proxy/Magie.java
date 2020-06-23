package victor.training.oo.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Magie {
   public static void main(String[] args) {
      Mate mateImpl = new Mate();
//      InvocationHandler h = new InvocationHandler() {
//         @Override
//         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            System.out.println("Cheama clientu metoda " + method.getName() + " cu param " + Arrays.toString(args));
//            return method.invoke(mateImpl, args);
//         }
//      };
      // doar pentru interfete
//      Mate mate = (Mate) Proxy.newProxyInstance(
//          Magie.class.getClassLoader(),
//          new Class<?>[]{Mate.class},
//          h);

      Callback callback = new MethodInterceptor() {
         @Override
         public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            System.out.println("Cheama clientu metoda " + method.getName() + " cu param " + Arrays.toString(args));
            return method.invoke(mateImpl, args);
         }
      };
      Mate mate = (Mate) Enhancer.create(Mate.class, callback);

      // pe sub, CGLIB face exact asta:
//      Mate mate = new Mate() {
//         @Override
//         public int suma(int a, int b) {
//            System.out.println("Cheama clientu metoda " + "suma" + " cu param " + a  + "," + b);
//            return mateImpl.suma(a, b);
//         }
      // TODO celelalte metode
//      };

      businessLogic(mate);
   }

   private static void businessLogic(Mate mate) {
      System.out.println("Eu oare cu ce instanta de mate lucrez ? " + mate.getClass());
      System.out.println(mate.suma(1,1));
      System.out.println(mate.suma(2,0));
      System.out.println(mate.suma(3,-1));
      System.out.println(mate.product(2, 1));
      System.out.println(mate.suma(3,1));
   }
}
final class Mate {
   public int suma(int a, int b) {
      return a + b;
   }

   public int product(int a, int b) {
      return a * b;
   }
}