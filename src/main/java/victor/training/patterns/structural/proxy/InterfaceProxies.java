package victor.training.patterns.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class InterfaceProxies {
   public static void main(String[] args) {

      InvocationHandler h = new InvocationHandler() {
         @Override
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(method.getName() + " with args " + Arrays.toString(args));
            return 3;
         }
      };
      // JDK proxies generate objs implementing arbitrary interfaces at RUNTIME
      Maths obj = (Maths) Proxy.newProxyInstance(InterfaceProxies.class.getClassLoader(),
          new Class<?>[]{Maths.class}, h);

      System.out.println(obj.sum(2, 1));
      System.out.println(obj.product(3, 1));
   }
}


interface Maths {
   int sum(int a, int b);

   int product(int a, int b);
}