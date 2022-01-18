package victor.training.patterns.structural.proxy;

public class ProxyuriDeClase {

   public static void main(String[] args) {
      // in matzele lui, spring sau Guice face:
      Matematica matematica = new Matematica();
      Matematica intermediar = new Matematica() {
         @Override
         public int suma(int a, int b) {
            System.out.println("SRI: Suma intre " + a + "  si " + b);
            return matematica.suma(a, b); // niciodata super.suma()
         }
      };
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
   }
}


class Matematica {
   public int suma(int a, int b) {
      return a + b;
   }

   public int produs(int a, int b) {
      return a * b;
   }
}