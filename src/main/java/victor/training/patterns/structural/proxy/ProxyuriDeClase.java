package victor.training.patterns.structural.proxy;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ProxyuriDeClase {

   public static void main(String[] args) {
      // in matzele lui, spring sau Guice face:
      Matematica matematicaReala = new Matematica();
//      Matematica intermediar = new Matematica() {
//         @Override
//         public int suma(int a, int b) {
//            System.out.println("SRI: Suma intre " + a + "  si " + b);
//            return matematica.suma(a, b); // niciodata super.suma()
//         }
//      };

//      java.lang.reflect.Proxy permite sa generezi implem dinamice de interfete

      Callback invocationHandler = new InvocationHandler() {
         @Override
         public Object invoke(Object o, Method method, Object[] args) throws Throwable {
            System.out.println("SRI: Calling " + method.getName() + " with args " + Arrays.toString(args));
            return method.invoke(matematicaReala, args);
         }
      };
      // linia urmatoare genereaza bytecodul unei subclase a Matematica, @Overridand toate met publice sa trimita la `invocationHandler`
      Matematica intermediar = (Matematica) Enhancer.create(Matematica.class, invocationHandler);

      OClasaDinSistemulTau x = new OClasaDinSistemulTau(intermediar);
      x.logica();
   }
}


class OClasaDinSistemulTau {
   private final Matematica matematica;

   OClasaDinSistemulTau(Matematica matematica) {
      this.matematica = matematica;
   }

   public void logica() {
      int salariulMarit = matematica.suma(500, 200); // apel ce vrem sa-l interceptam!
      System.out.println(salariulMarit);

      System.out.println("sase = " + matematica.produs(2, 3));
   }
}


/*final*/ class Matematica {
   public /*final*/ int suma(int a, int b) {
      return a + b;
   }

   public int produs(int a, int b) {
      int produs = 0;
      for (int i = 0; i < a; i++) {
         produs = suma(produs, b); // acest apel de `suma` NU poate fi interceptat de niciun proxy standard
         // CAPCANA PRINCIPALA in folosirea proxyurilor este ca apeluri locale de metode NU SUNT INTERCEPTATE (nu trec prin proxy).
      }
      return produs;
   }
}