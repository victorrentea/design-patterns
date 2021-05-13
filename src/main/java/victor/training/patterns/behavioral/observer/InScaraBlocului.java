package victor.training.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class InScaraBlocului {
   public static void main(String[] args) {

      Baba floarea = new Baba();

      floarea.abonez(new DnConstantinesc());
      floarea.abonez(new SectoristulDeServiciu());


      floarea.oVecinaIntraInScaraCuUnu("Rita cu un pletos");
   }
}

class Baba {
   List<Barfitor> barfitorList = new ArrayList<>();

   public void abonez(Barfitor barfitor) {
      barfitorList.add(barfitor);
   }

   public void oVecinaIntraInScaraCuUnu(String hotnews) {
      for (Barfitor barfitor : barfitorList) {
         barfitor.afla(hotnews);
      }
   }
}

class DnConstantinesc implements Barfitor {

   @Override
   public void afla(String barfa) {
      System.out.println("Afla dc const " + barfa);
   }
}
// --------------------------- linie ------------------------------

class SectoristulDeServiciu implements Barfitor {

   @Override
   public void afla(String barfa) {
      System.out.println("Raporteaza mai departe " + barfa);
   }
}

interface Barfitor {
   void afla(String barfa);
}