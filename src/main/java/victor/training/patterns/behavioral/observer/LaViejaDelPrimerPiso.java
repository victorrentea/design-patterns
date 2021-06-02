package victor.training.patterns.behavioral.observer;

import java.util.ArrayList;
import java.util.List;


class Vieja {
   List<Listener> listeners = new ArrayList<>();

   public void addListener(Listener listener) {
      listeners.add(listener);
   }

   public void findsOutSomethingRemarkable(String something) {
      for (Listener listener : listeners) {
         listener.findOut(something);
      }
   }
}

class MarioDelSegundo implements Listener {

   @Override
   public void findOut(String gossip) {
      System.out.println("Mario : " + gossip);
   }
}
// --------------- Architecture: the art of drawing lines -----------------

class LaPoliciaDelBario implements Listener {

   @Override
   public void findOut(String gossip) {
      System.out.println("Policia : " + gossip);
   }
}

public class LaViejaDelPrimerPiso {
   public static void main(String[] args) {

      Vieja vieja = new Vieja();
      vieja.addListener(new MarioDelSegundo());
      vieja.addListener(new LaPoliciaDelBario());
      vieja.findsOutSomethingRemarkable("Rita came home with a Rocker with long hair !");
   }
}


interface Listener {
   void findOut(String gossip);
}
