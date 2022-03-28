package victor.training.patterns.structural.proxy;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ClassProxies {
   public static void main(String[] args) {
      Maths realMaths = new Maths();

      // please imagine this code is ran by your DI container
      Callback handler = new InvocationHandler() {
         @Override
         public Object invoke(Object o, Method method, Object[] arguments) throws Throwable {
            System.out.println("Calling method " + method.getName() + " with args: " + Arrays.toString(arguments));
            return method.invoke(realMaths, arguments);
         }
      };
      /// this variable kees a proxy to the real maths instance
      Maths maths = (Maths) Enhancer.create(Maths.class, handler);

      //      ClientCode logic = new ClientCode(new MathsDecorator(maths));
      ClientCode logic = new ClientCode(maths);

      logic.method();
   }
}

// @Service
class ClientCode {
   private final Maths maths;

   ClientCode(Maths maths) {
      this.maths = maths;
   }

   public void method() {
      System.out.println("3 = " + maths.sum(1, 2));
      System.out.println("4 = " + maths.product(2, 2));
      System.out.println("4 = " + maths.sqr(3));
   }
}

//class already there that I want to change its methods' behavior
class Maths {
   public int sum(int a, int b) {
      return a + b;
   }

   @Cacheable("a")
   public int product(int a, int b) {
      return a * b;
   }

   public int sqr(int a) {
      return a * a;
   }
}