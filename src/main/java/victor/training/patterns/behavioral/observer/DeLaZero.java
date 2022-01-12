package victor.training.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

class BabaDelaParter {
   private List<Subscriber> subscribers = new ArrayList<>();

   public void adaugaSubscriber(Subscriber barfitor) {
      subscribers.add(barfitor);
   }

   public void aflaNoutate(String barfa) {
      System.out.println("Baba face broadcast");
      for (Subscriber subscriber : subscribers) {
         subscriber.afla(barfa);
      }
   }
}

// nimic de deasupra liniei astea nu stie de ce impl de barfitor exista in sistem
// tipic parte dintr-o libarie.
// ---------------- o linie:-------------
public class DeLaZero {

   public static void main(String[] args) {
      BabaDelaParter b = new BabaDelaParter();
      b.adaugaSubscriber(new CostelDeLa2());
      b.adaugaSubscriber(new Agale());

      b.aflaNoutate("Rita a venit acasa cu un pletos");

   }

}

class CostelDeLa2 implements Subscriber {
   @Override
   public void afla(String barfa) {
      System.out.println("Costel afla ca:" + barfa);
   }
}

class Agale implements Subscriber {
   @Override
   public void afla(String barfa) {
      System.out.println("Agale afla ca:" + barfa);
   }
}

interface Subscriber {
   void afla(String barfa);
}