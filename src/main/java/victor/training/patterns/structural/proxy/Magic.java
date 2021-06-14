package victor.training.patterns.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Magic {
   public static void main(String[] args) throws IOException {

      Math real = new Math();


//      Writer writer = new FileWriter("a");
//      writer = new BufferedWriter(writer);
//
//      List<String> list= Arrays.asList("adsa");
//
//      List<String> strings = Collections.unmodifiableList(list);
//      list.add("a");


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

//class MathLoggingDecorator implements IMath{
//   private final IMath delegate;
//
//   MathLoggingDecorator(IMath delegate) {
//      this.delegate = delegate;
//   }
//
//   @Override
//   public int sum(int a, int b) {
//      log.debug();
//      return delegate.sum(a,b);
//   }
//
//   @Override
//   public int multiply(int a, int b) {
//      log.debug();
//      return 0;
//   }
//}
//
//interface IMath {
//
//   int sum(int a, int b);
//
//   int multiply(int a, int b);
//}
class Math {
   public int sum(int a, int b) {
      return a + b;
   }

   public int multiply(int a, int b) {
      return a * b;
   }
}
