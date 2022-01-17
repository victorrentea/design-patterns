package victor.training.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;


class BabaSectorist {
   List<Barfitor> subscribers = new ArrayList<>();

   public void add(Barfitor barfitor) {
      subscribers.add(barfitor);
   }

   //   @EventListener(ApplicationReadyEvent.class)
   public void aflaCevaScocant(String barfa) {
      for (Barfitor subscriber : subscribers) {
         subscriber.barfeste(barfa);
      }
   }
}

public class ObserverBarfa {
   public static void main(String[] args) {
      BabaSectorist babaSectorist = new BabaSectorist();

      babaSectorist.add(new CostelDeLa4());
      babaSectorist.add(new AglaeDeLa4());

      babaSectorist.aflaCevaScocant("Rita a venit acasa cu un pletos");
   }

}

///////////////// ---------------------- linie --------------------

class CostelDeLa4 implements Barfitor {
   @Override
   public void barfeste(String barfa) {
      System.out.println("Costel afla : " + barfa);
   }
}

class AglaeDeLa4 implements Barfitor {
   @Override
   public void barfeste(String barfa) {
      System.out.println("Aglae afla : " + barfa);
   }
}

interface Barfitor {
   void barfeste(String barfa);
}
