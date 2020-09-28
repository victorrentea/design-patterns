package victor.training.oo.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ProxiereDeClase {

   public static void main(String[] args) {

      Mate mateImpl = new Mate();
//      InvocationHandler callHandler = new InvocationHandler() {
//         @Override
//         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            System.out.println("Trec prin proxy catre metoda " + method.getName() + " cu param " + Arrays.toString(args));
//            return method.invoke(mateImpl, args);
//         }
//      };

//      Mate mate = (Mate) Proxy.newProxyInstance(ProxiereDeInterfete.class.getClassLoader(),
//          new Class<?>[]{Mate.class}, callHandler);
      Callback methodCallback = new MethodInterceptor() {
         @Override
         public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
            System.out.println("Trec prin proxy catre metoda " + method.getName() + " cu param " + Arrays.toString(args));
            return method.invoke(mateImpl, args);
         }
      };
      Mate mate = (Mate) Enhancer.create(Mate.class, methodCallback);
      // generates a dynamic subclass of your concrete class
//      m(new Mate(){
//         @Override
//         public int suma(int a, int b) {
//            return super.suma(a, b);
//         }
//      });

      m(mate);

   }

   private static void m(Mate mate) {
      System.out.println("Oare cu ce clasa de mate vorbesc ? " + mate.getClass());
      System.out.println(mate.suma(1, 1));
      System.out.println(mate.suma(2, 0));
      System.out.println(mate.suma(3, -1));
      System.out.println(mate.produs(-2, -1));

      System.out.println(mate.suma(3, 1));
   }

}

//cum o hackui: vezi comentarii mai jos
/*final */class Mate {
   public /*static*/  /*final*/ int suma(int a, int b) {
      System.out.println("In metoda reala!");
      return a+b;
   }

   public int produs(int a, int b) {
      return a*b;
   }
}
