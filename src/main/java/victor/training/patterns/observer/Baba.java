package victor.training.patterns.observer;


import java.util.ArrayList;
import java.util.List;


/// ---------------------------------------------------------------------------------------
public class Baba {
   private final List<Barfitor> barfitori = new ArrayList<>();

   public void subscribe(Barfitor barfitor) {
      barfitori.add(barfitor);
   }

   public void aflaCevaInteresant(String breakingNews) {
      for (Barfitor barfitor : barfitori) {
         barfitor.spune(breakingNews);
      }
   }
}

interface Barfitor {
   void spune(String barfa);
}
/// ---------------------------------------------------------------------------------------


class CostelDeLa2 implements Barfitor {
   @Override
   public void spune(String barfa) {
      System.out.println("COstel afla " + barfa);
   }
}

class AglaeDela5 implements Barfitor {
   @Override
   public void spune(String barfa) {
      System.out.println("Aglae: afla " + barfa);
   }
}

class JoacaCuBaba {
   public static void main(String[] args) {

      Baba baba = new Baba();

      baba.subscribe(new CostelDeLa2());
      baba.subscribe(new AglaeDela5());

      baba.aflaCevaInteresant("Rita a venit acasa cu un pletos");
   }
}