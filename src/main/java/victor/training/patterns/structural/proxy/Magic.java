package victor.training.patterns.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Magic {
   public static void main(String[] args) {

      Math real = new Math();

      Callback callback = new MethodInterceptor() {
         @Override
         public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("Calling " + method.getName() + " with arg " + Arrays.toString(objects));
            Object r = method.invoke(real, objects);
            System.out.println("returning " + r);
            return r;
         }
      };
      Math math = /*new Math() {
         @Override
         public int sum(int a, int b) {
            return super.sum(a, b);
         }
      };*/ (Math) Enhancer.create(Math.class, callback);
      bizLogic(math);
   }

   //@Autowired
//Math math;
   public static void bizLogic(Math math) {
      System.out.println(math.sum(1, 1));
      System.out.println(math.multiply(2, 2));
   }

}

class Math {
   public int sum(int a, int b) {
      return a + b;
   }

   public int multiply(int a, int b) {
      return a * b;
   }
}
