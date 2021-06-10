package victor.training.patterns.structural.proxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Magic {
   public static void main(String[] args) {

      Maths mathReal = new Maths();

      // hacks your math instance
      Callback callback = new MethodInterceptor() {
         @Override
         public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("Calling method " + method.getName() + " with args " + Arrays.toString(objects));
            Object r = method.invoke(mathReal, objects);
            System.out.println("Returing " + r);
            return r;
         }
      };
//      Maths maths = new Maths() {
//         @Override
//         public int sum(int a, int b) {
//            return super.sum(a, b);
//         }
//
//         @Override
//         public int product(int a, int b) {
//            return super.product(a, b);
//         }
//      };
      Maths maths = (Maths) Enhancer.create(Maths.class, callback);

      bizLogic(maths);
   }

   private static void bizLogic(Maths maths) {
      System.out.println(maths.sum(1, 1));
      System.out.println(maths.sum(1, 2));
      System.out.println(maths.product(1, 2));
      System.out.println(maths.product(3, 2));
   }
}

class Maths {
   private static final Logger log = LoggerFactory.getLogger(Maths.class);

   public int sum(int a, int b) {
      return a + b;
   }

   public int product(int a, int b) {
      return a * b;
   }
}
