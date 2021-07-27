package victor.training.patterns.creational.factory;

import victor.training.patterns.stuff.ThreadUtils;

import java.util.Objects;

import static java.lang.System.exit;

public class FactoriesStart {
   public static void main(String[] args) {
      LovingParent parent = new LovingParent(new Child());
      parent.finishWorkExhausted();
   }
}


class LovingParent {
   private final Child child;

   public LovingParent(Child child) {
      this.child = child;
   }

   public void finishWorkExhausted() {

      Toy toy = ToyShop.buyToy();
//      toy.us


   }
}

class ToyShop {
   private static boolean maiAmCiocane = true; // close by

   public static Toy buyToy() {
      if (maiAmCiocane) return new ToyHammer();
      else return new ToyAxe();
   }
}





class Child {

   public void noticeAndDrainParent() {
      System.out.println("Drain parent energy");
      exit(-1);
   }

   public void play() {
      Object toy = null;
      Objects.requireNonNull(toy, "Kid too young, unable to play alone. Try a toy!");
      System.out.println("Playing with " + toy + " ...");
      ThreadUtils.sleepq(1000);
      System.out.println("Done Playing");
   }
}

class ToyHammer implements Toy {
   public void use() {
      System.out.println("Bang-Bang!");
   }
}

class ToyAxe implements Toy {
   public void use() {
      System.out.println("Harh Harh");
   }
}

interface Toy {
   void use();
}
