package victor.training.patterns.behavioral.observer;


import java.util.ArrayList;
import java.util.List;

public class SectoristuDeServiciu {
   public static void main(String[] args) {
      Baba baba = new Baba();
      baba.subscribe(new PolitiaDeProximitate());
      baba.subscribe(new BabaDeLa4());

      baba.afluCevaEsential("A venit Rita cu un pletos");
   }
}


class Baba {
   private final List<Subscriber> subscribers = new ArrayList<>();
   public void subscribe(Subscriber subscriber) {
      subscribers.add(subscriber);
   }
   public void afluCevaEsential(String barfa) {
      for (Subscriber subscriber : subscribers) {
         subscriber.notify(barfa);
      }
   }
}
interface Subscriber {
   void notify(String barfa);
}
class PolitiaDeProximitate implements Subscriber{
   @Override
   public void notify(String barfa) {
      System.out.println("Plutonierul " + barfa) ;
   }
}

class BabaDeLa4 implements Subscriber {

   @Override
   public void notify(String barfa) {
      System.out.println("4 " + barfa) ;
   }
}