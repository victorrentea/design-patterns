package victor.training.patterns.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class HaiSaMaJoc {
   public static void main(String[] args) {
      Mate realMate = new Mate();

      InvocationHandler h = new InvocationHandler() {
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("SRI: Se cheama metoda " + method.getName() + " " + Arrays.toString(args));
            long t0 = System.currentTimeMillis();
            Object result = method.invoke(realMate, args);
            long t1 = System.currentTimeMillis();
            System.out.println("Took "+ (t1-t0));
            return result;
         }
      };

//      Mate mate = (Mate) Proxy.newProxyInstance(HaiSaMaJoc.class.getClassLoader(),
//          new Class<?>[] {Mate.class}, h);

      Callback callback = new MethodInterceptor() {
         @Override
         public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            System.out.println("SRI: Se cheama metoda " + method.getName() + " " + Arrays.toString(args));
            long t0 = System.currentTimeMillis();
            Object result = method.invoke(realMate, args);
            long t1 = System.currentTimeMillis();
            System.out.println("Took "+ (t1-t0));
            return result;
         }
      };
      Mate mate = (Mate) Enhancer.create(Mate.class, callback);

//      mate = new Mate() {
//         @Override
//         public int sum(int a, int b) {
//            System.out.println("Hop si eu");
//            return super.sum(a, b);
//         }
//      };

      coduClient(mate);
   }

   public static void coduClient(Mate mate) {
      System.out.println(mate.getClass());
      System.out.println(mate.sum(1, 1));
      System.out.println(mate.sum(2, 0));
      System.out.println(mate.sum(3, -1));
      System.out.println(mate.sum(3, 1));
      System.out.println(mate.product(2, 2));
   }
}


class Mate {
   public int sum(int a, int b) {
      return a + b;
   }
   public int product(int a, int b) {
      return a * b;
   }
}

