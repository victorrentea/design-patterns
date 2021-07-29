package victor.training.patterns.structural.proxy;

import org.springframework.cache.annotation.Cacheable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class Proxyuri {
   public static void main(String[] args) {
      MateImpl impl = new MateImpl();
      InvocationHandler handler = new InvocationHandler() {
         @Override
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.isAnnotationPresent(Cacheable.class)) {

            }
            System.out.println("Se cheama metoda " + method.getName() + " cu args " + Arrays.toString(args));
            return method.invoke(impl, args);
         }
      };

      Mate mate = (Mate) Proxy.newProxyInstance(Proxyuri.class.getClassLoader(),
          new Class<?>[]{Mate.class}, handler);

      System.out.println(mate.sum(1, 1));
      System.out.println(mate.sum(1, 3));
      System.out.println(mate.sum(2, 0));
      System.out.println(mate.product(2, 1));
   }
}

class MateImpl implements Mate {
   public int sum(int a, int b) {
      return a + b;
   }

   public int product(int a, int b) {
      return a * b;
   }
}

interface Mate {
   int sum(int a, int b);

   int product(int a, int b);
}