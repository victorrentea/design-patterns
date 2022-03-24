package victor.training.patterns.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ClassProxies {
   public static void main(String[] args) {
      Maths realMaths = new Maths();
      // only works to create interfaces>!!! "JDK proxies"
//      InvocationHandler callHandler = new InvocationHandler() {
//         @Override
//         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            System.out.println("Calling " + method.getName() + " with args " + Arrays.toString(args));
//            return method.invoke(realMaths, args);
//         }
//      };
//      Maths maths = (Maths) Proxy.newProxyInstance(InterfaceProxies.class.getClassLoader(),
//          new Class<?>[]{Maths.class}, callHandler);

      // proxy of concrete classes "CGLIB proxies"
      Callback callHandler = new InvocationHandler() {
         @Override
         public Object invoke(Object o, Method method, Object[] args) throws Throwable {
            System.out.println("Calling " + method.getName() + " with args " + Arrays.toString(args));
            return method.invoke(realMaths, args);
         }
      };
      Maths maths = (Maths) Enhancer.create(Maths.class, callHandler);
      // generates code like this:
//      Maths math = new Maths() {
//         @Override
//         public int sum(int a, int b) {
//            return super.sum(a, b);
//         }
//      }

      // spring here
      new YourService(maths).method();
   }
}

//@Service
class YourService {
   private final Maths maths; // a dynamicaly generated subclass of the desired bean is injected.

   YourService(Maths maths) {
      System.out.println("your dependencies are not what they seem to be : " + maths.getClass());
      this.maths = maths;
   }

   public void method() {
      System.out.println(maths.sum(1, 2));
      System.out.println(maths.product(1, 2));
   }
}

//@Service
final class Maths {
   public int sum(int a, int b) {
      return a + b;
   }

   public int product(int a, int b) {
      return a * b;
   }
}