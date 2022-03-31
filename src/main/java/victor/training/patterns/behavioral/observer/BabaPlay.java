package victor.training.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;



public class BabaPlay {
   public static void main(String[] args) {
      Baba baba = new Baba();
      baba.adaugaBarfitor(new CostelDeLa2());
      baba.adaugaBarfitor(new Ksiusha());

      baba.aflat("Rita a venit cu un pletos");
   }
}

// linie --------------------------------------------------
class Baba {
   private final List<Barfitor> subscribers = new ArrayList<>();

   public void aflat(String  cevaInterenta) {
      for (Barfitor barfitor : subscribers) {
         barfitor.anunta(cevaInterenta);
      }
   }
   public void adaugaBarfitor(Barfitor barfitor) {
      subscribers.add(barfitor);
   }
}
interface Barfitor {
   void anunta(String barfa);
}
// linie --------------------------------------------------


class CostelDeLa2 implements Barfitor {
   @Override
   public void anunta(String barfa) {
      System.out.println("Costel afla " + barfa);
   }
}

class Ksiusha implements Barfitor {
   @Override
   public void anunta(String barfa) {
      System.out.println("Ksiusha afla " + barfa);
   }
}