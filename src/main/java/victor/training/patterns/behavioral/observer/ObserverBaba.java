package victor.training.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class ObserverBaba {
   public static void main(String[] args) {
      Baba baba = new Baba();
      baba.add(new CostacheDeLa3());
      baba.add(new Sectorist());

      baba.aflaBarfa("Rita a venit cu un pletos cu Metallica pe el.");

   }
}


class Baba {
   private List<Barfitor> barfitori = new ArrayList<>();

   public void add(Barfitor barfitor) {
      barfitori.add(barfitor);
   }

   public void aflaBarfa(String barfa) {
      for (Barfitor barfitor : barfitori) {
         barfitor.afla(barfa);
      }
   }
}

class CostacheDeLa3 implements Barfitor {
   public void afla(String barfa) {
      System.out.println("Costache: " + barfa);
   }
}
//-------------------- (o linie:)------------------------------

class Sectorist implements Barfitor {
   public void afla(String barfa) {
      System.out.println("SRI: " + barfa);
   }
}

interface Barfitor {
   void afla(String barfa);
}