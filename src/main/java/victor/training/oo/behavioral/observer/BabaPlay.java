package victor.training.oo.behavioral.observer;

import java.util.ArrayList;
import java.util.List;


class Baba {
   List<InteresatDeBarfe> observers = new ArrayList<>();


   public void addObserver(InteresatDeBarfe observer) {
      observers.add(observer);
   }

   public void aflaCeva(String barfa) {
      for (InteresatDeBarfe observer : observers) {
         observer.anuntaBarfa(barfa);
      }
   }
}

interface InteresatDeBarfe {
   void anuntaBarfa(String barfa);
}
// -----------------------------------------
class Persoana1 implements InteresatDeBarfe {
   public void anuntaBarfa(String barfa) {
      System.out.println("Aflu de " + barfa + ". Eu sunt = " + getClass());
   }
}
class Persoana2 implements InteresatDeBarfe {
   public void anuntaBarfa(String barfa) {
      System.out.println("Aflu de " + barfa + ". Eu sunt = " + getClass());
   }
}
class Persoana3 implements InteresatDeBarfe {
   public void anuntaBarfa(String barfa) {
      System.out.println("Aflu de " + barfa + ". Eu sunt = " + getClass());
   }
}
public class BabaPlay {
   public static void main(String[] args) {

      Baba baba = new Baba();
      baba.addObserver(new Persoana1());
      baba.addObserver(new Persoana2());
      baba.addObserver(new Persoana3());
      baba.aflaCeva("Rita a venit acasa cu un pletos");
   }

}