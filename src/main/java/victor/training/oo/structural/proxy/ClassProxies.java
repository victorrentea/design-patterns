package victor.training.oo.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ClassProxies {
   public static void main(String[] args) {

      Mate mateImpl = new Mate();

      Callback callback = new MethodInterceptor() {
         @Override
         public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            System.out.println("Cheama metoda " + method.getName() + "(" + Arrays.toString(objects) + ")");
            return method.invoke(mateImpl, objects);
         }
      };
      Mate mate= (Mate) Enhancer.create(Mate.class, callback); // genereaza bytecodul unei subclase a Mate, care face override la tot ce-i public

      // similar
//      mate = new Mate(){
//         @Override
//         public int suma(int a, int b) {
//            return super.suma(a, b);
//         }
//
//         @Override
//         public int produs(int a, int b) {
//            return super.produs(a, b);
//         }
//      };


      System.out.println(mate.suma(1,1));
      System.out.println(mate.suma(2,0));
      System.out.println(mate.suma(3,-1));
      System.out.println(mate.produs(2,1));

      System.out.println(mate.suma(3,1));
   }
}

class Mate {

   public int suma(int a, int b) {
      return a + b;
   }

   public int produs(int a, int b) {
      return a * b;
   }
}

