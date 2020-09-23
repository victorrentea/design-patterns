package victor.training.oo.structural.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyDeInterfete {
   static I dep;
   public static void main(String[] args) {

      dep = new Implem();

      dep = proxiez(dep);

      System.out.println(dep.plus(1));
   }

   private static I proxiez(I prevImplem) {
      InvocationHandler genericHandlerForAnyMethod = new InvocationHandler() {
         @Override
         public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println(method.getName());
            System.out.println(method.getDeclaringClass().getCanonicalName());
            return method.invoke(prevImplem, args);
         }
      };
      return (I) Proxy.newProxyInstance(ProxyDeInterfete.class.getClassLoader(), new Class<?>[]{I.class},
          genericHandlerForAnyMethod);
   }
}
interface I {
   Integer plus(Integer i);
   Integer minus(Integer i);
}
class Implem implements I{

   @Override
   public Integer plus(Integer i) {
      System.out.println("In metoda reala");
      return i + 1;
   }
   @Override
   public Integer minus(Integer i) {
      return i-1;
   }
}