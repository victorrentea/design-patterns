package victor.training.patterns.structural.proxy;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.retry.annotation.Retryable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Magic {
   public static void main(String[] args) {
      Math realStuff = new Math();

//      InvocationHandler h = new InvocationHandler() {
//         @Override
//         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            System.out.println("Calling method " + method.getName() + " with args " + Arrays.toString(args));
//            return method.invoke(realStuff, args);
//         }
//      };
//      Math math = (IMath) Proxy.newProxyInstance(Magic.class.getClassLoader(),
//          new Class<?>[]{IMath.class},
//          h
//      );
      Callback callback = new org.springframework.cglib.proxy.InvocationHandler() {
         @Override
         public Object invoke(Object o, Method method, Object[] args) throws Throwable {
            System.out.println("Calling method " + method.getName() + " with args " + Arrays.toString(args));
            return method.invoke(realStuff, args);
         }
      };
      Math math = (Math) Enhancer.create(Math.class, callback);

      System.out.println(math.sum(1,1));
      System.out.println(math.sum(2,0));
      System.out.println(math.sum(3,-1));

      System.out.println(math.sum(3,1));
      System.out.println(math.product(2,1));
   }
}

class Math {
   public int sum(int a, int b) {
      return a+b;
   }
   public int product(int a, int b) {
      return a*b;
   }
}