package victor.training.patterns.structural.proxy;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Method;
import java.util.Arrays;

public class LetsPlay {
   public static void main(String[] args) {

      MathImpl realImpl = new MathImpl();
//      InvocationHandler handler = new InvocationHandler() {
//         @Override
//         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            System.out.println("Calling " + method.getName() + " with args " + Arrays.toString(args));
//            return method.invoke(realImpl, args);
//         }
//      };

//      Math math = (Math) Proxy.newProxyInstance(LetsPlay.class.getClassLoader(),
//          new Class<?>[]{Math.class}, handler);

      Callback handler = new InvocationHandler() {
         @Override
         public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
            System.out.println("Calling " + method.getName() + " with args " + Arrays.toString(objects));
            return method.invoke(realImpl, objects);
         }
      };
      MathImpl math = (MathImpl) Enhancer.create(MathImpl.class, handler);

      demo(math);
   }

   private static void demo(MathImpl math) {
      System.out.println(math.sum(1, 1));
      System.out.println(math.sum(2, 0));
      System.out.println(math.sum(3, -1));
      System.out.println(math.sum(3, 1));
      System.out.println(math.product(2, 1));
   }
}

class MathImpl {

   @Transactional
   @Cacheable
   public int sum(int a, int b) {
      return a + b;
   }

   public int product(int a, int b) {
      return a * b;
   }
}


