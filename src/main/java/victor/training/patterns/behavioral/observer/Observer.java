package victor.training.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

public class Observer {
   public static void main(String[] args) {
      Baba baba = new Baba();
      baba.subscribe(new PolitiaDeProximitate());
      baba.subscribe(new Hackerul());

      baba.afla("Rita a venit cu un pletos");
   }
}
class Baba {
   private final List<Abonat> abonati = new ArrayList<>();
   public void subscribe(Abonat abonat) {
      abonati.add(abonat);
   }
   public void afla(String barfa) {
      for (Abonat abonat : abonati) {
         abonat.anunta(barfa);

      }
   }
}

interface Abonat {
   void anunta(String barfa);
}

///// ------------------- linie
class PolitiaDeProximitate implements  Abonat{
   @Override
   public void anunta(String barfa) {
      System.out.println("Garcea: " + barfa);
   }
}
class Hackerul implements Abonat {
   @Override
   public void anunta(String barfa) {
      System.out.println("hack: " + barfa);
   }
}