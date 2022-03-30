package victor.training.patterns.structural.proxy;

import lombok.RequiredArgsConstructor;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

public class ClassProxy {
   public static void main(String[] args) {

      Mate mate = new Mate();

      // JDK proxy : genereaza ob care implem interfete "on the fly", delegand toate apelurile la orice metoda catre 'h'.invoke()
      Callback h = new MethodInterceptor() {
         @Override
         public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
//            db.insert()
//            rest.call(kgb)
            System.out.println("SIS: cheama fraeru metoda " + method.getName() + " cu param " + Arrays.toString(args));
            return method.invoke(mate, args);
         }
      };
      Mate proxy = (Mate) Enhancer.create(Mate.class, h);
//          new Mate() {
//         @Override
//         public int sum(int a, int b) {
//            //chestii
//            return super.sum(a, b);
//         }
//
//         @Override
//         public int produs(int a, int b) {
//            return super.produs(a, b);
//         }
//      };

      CodClient codClient = new CodClient(proxy);
      codClient.clasa2();
   }
}


@Service
@RequiredArgsConstructor
class CodClient {
   private final Mate mate;

   public void clasa2() {
      System.out.println("Oare cu cine vorbesc: " + mate.getClass());
      System.out.println(mate.sum(1000, 1000));
      System.out.println(mate.produs(7, 8));
   }
}


@Service
class Mate  {

   public int sum(int a, int b) {
      return a + b;
   }
   public int produs(int a, int b) {
      return a * b;
   }
}