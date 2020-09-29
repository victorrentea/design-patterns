package victor.training.oo.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

// Subject
public class DoamnaDeLaParter {
   public static void main(String[] args) {
      DoamnaDeLaParter doamna = new DoamnaDeLaParter();
      doamna.addObserver(new GhitaDeLa1());
      doamna.addObserver(new MariaDeLa2());
   }

   private List<Observer> observers = new ArrayList<>();
   public void addObserver(Observer observer) {
      observers.add(observer);
   }
   public void afla(String barfa) {
      for (Observer observer : observers) {
         observer.notify(barfa);
      }
   }
}
interface Observer {
   void notify(String event);
}
// ============================================
// orice nou observer implica doar modificari sub aceasta linie.

class GhitaDeLa1 implements Observer {
   @Override
   public void notify(String event) {
      System.out.println("Ghita afla ca " + event);
   }
}
class MariaDeLa2 implements Observer {
   @Override
   public void notify(String event) {
      System.out.println("Maria afla ca " + event);
   }
}