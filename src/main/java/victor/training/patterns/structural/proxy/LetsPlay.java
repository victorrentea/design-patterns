package victor.training.patterns.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class LetsPlay {
   public static void main(String[] args) {

      MathImpl realImpl = new MathImpl();
      InvocationHandler handler = new InvocationHandler() {
         @Override
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Calling " + method.getName() + " with args " + Arrays.toString(args));
            return method.invoke(realImpl, args);
         }
      };

      Math math = (Math) Proxy.newProxyInstance(LetsPlay.class.getClassLoader(),
          new Class<?>[]{Math.class}, handler);

      demo(math);
   }

   private static void demo(Math math) {
      System.out.println(math.sum(1, 1));
      System.out.println(math.sum(2, 0));
      System.out.println(math.sum(3, -1));
      System.out.println(math.sum(3, 1));
      System.out.println(math.product(2, 1));
   }
}

class MathImpl implements Math {

   @Override
   public int sum(int a, int b) {
      return a + b;
   }

   @Override
   public int product(int a, int b) {
      return a * b;
   }
}

interface Math {
   int sum(int a, int b);

   int product(int a, int b);
}


