//package victor.training.patterns.structural.proxy;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.lang.reflect.InvocationHandler;
//import java.lang.reflect.Method;
//import java.util.Arrays;
//
//public class InterfaceProxy {
//   public static void main(String[] args) {
//
//      Mate mate = new Mate();
//
//      InvocationHandler h = new InvocationHandler() {
//         @Override
//         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//            System.out.println("SIS: cheama fraeru metoda " + method.getName() + " cu param " + Arrays.toString(args));
////            if (method.isAnnotationPresent(Transactional.class)) {
////               //chestii
////            }
//            return method.invoke(mate, args);
//         }
//      };
//      // JDK proxy : genereaza ob care implem interfete "on the fly", delegand toate apelurile la orice metoda catre 'h'.invoke()
//      IMate decorator = (IMate) java.lang.reflect.Proxy.newProxyInstance(
//          InterfaceProxy.class.getClassLoader(),
//          new Class<?>[]{IMate.class},
//          h
//      );
//
//      CodClient codClient = new CodClient(decorator);
//      codClient.clasa2();
//   }
//}
//
////@RequiredArgsConstructor
////class MateDecorator implements IMate {
////   private final IMate delegate;
////   public int sum(int a, int b) {
////      System.out.println("SIS: sum("+a+","+b+")");
////      return delegate.sum(a,b);
////   }
////}
//// frameworkul (Spring)
////-------------------
//// codu tau, scris cu mainile TALE
//
//@Service
//@RequiredArgsConstructor
//class CodClient {
//   private final IMate mate;
//
//   public void clasa2() {
//      System.out.println(mate.sum(1000, 1000));
//      System.out.println(mate.produs(7, 8));
//   }
//}
//
//interface IMate {
//   int sum(int a, int b);
//   int produs(int a, int b);
//}
//
//@Service
//class Mate implements IMate {
//   public int sum(int a, int b) {
//      return a + b;
//   }
//
//   @Override
//   public int produs(int a, int b) {
//      return a * b;
//   }
//}

