package victor.training.patterns.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class InterfaceProxies {
   public static void main(String[] args) {
      Maths realMaths = new MathsImpl();
      InvocationHandler callHandler = new InvocationHandler() {
         @Override
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Calling " + method.getName() + " with args " + Arrays.toString(args));
            return method.invoke(realMaths, args);
         }
      };
      Maths maths = (Maths) Proxy.newProxyInstance(InterfaceProxies.class.getClassLoader(),
          new Class<?>[]{Maths.class}, callHandler);
      // spring here
      new YourService(maths).method();
   }
}

//@Service
class YourService {
   private final Maths maths;

   YourService(Maths maths) {
      this.maths = maths;
   }

   public void method() {
      System.out.println(maths.sum(1, 2));
      System.out.println(maths.product(1, 2));
   }
}

interface Maths {
   int sum(int a, int b);

   int product(int a, int b);
}

class MathsImpl implements Maths {
   @Override
   public int sum(int a, int b) {
      return a + b;
   }

   @Override
   public int product(int a, int b) {
      return a * b;
   }
}