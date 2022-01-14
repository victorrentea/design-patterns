package victor.training.patterns.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;
import java.util.Arrays;

public class FromScratch {


   public static void main(String[] args) {
      ExpensiveOps realInstance = new ExpensiveOps();

      Callback h = new InvocationHandler() {
         @Override
         public Object invoke(Object o, Method method, Object[] params) throws Throwable {
            System.out.println("Calling method " + method.getName() + " with args " + Arrays.toString(params));
            return method.invoke(realInstance, params);
         }
      };
      ExpensiveOps proxyWithBareHands = (ExpensiveOps) Enhancer.create(ExpensiveOps.class, h);

//      proxyWithBareHands = new ExpensiveOps() {
//         @Override
//         public Boolean isPrime(int n) {
//            System.out.println("Stuff");
//            return super.isPrime(n);
//         }
//      };

      System.out.println(proxyWithBareHands.isPrime(2));
      System.out.println(proxyWithBareHands.isPrime(3));
      System.out.println(proxyWithBareHands.isPrime(5));
      System.out.println(proxyWithBareHands.isPrime(4));

   }
}
