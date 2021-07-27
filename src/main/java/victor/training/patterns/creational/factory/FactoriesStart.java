package victor.training.patterns.creational.factory;

import victor.training.patterns.stuff.ThreadUtils;

import java.util.Objects;

public class FactoriesStart {
   public static void main(String[] args) {
      LovingParent parent = new LovingParent();
      parent.finishWorkExhausted();
   }
}


class LovingParent {
   public void finishWorkExhausted() {
      Child.play(null);
   }
}

class ToyShop {
   public static ToyHammer buyToy() {
      return new ToyHammer();
   }
}

class Child {
   public static void play(Object toy) {
      Objects.requireNonNull(toy, "Unable to play alone: Kid too young. Try a toy!");
      System.out.println("Playing with " + toy + " ...");
      ThreadUtils.sleepq(1000);
      System.out.println("Done Playing");
   }
}

class ToyHammer {
   public void use() {
      System.out.println("Bang-Bang!");
   }
}
