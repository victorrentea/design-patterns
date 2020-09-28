package victor.training.oo.creational.factory;

import victor.training.oo.stuff.ThreadUtils;

import static java.lang.System.exit;

public class FactoriesStart {
   public static void main(String[] args) {
      LovingParent parent = new LovingParent(new Child());

      parent.finishWorkExhausted();
   }
}

interface JocCuCuburi {

}
class LegoGame implements JocCuCuburi {
}

class MagazinDeJucarii {
   static int legoStock = 1;

   public static JocCuCuburi cumparaJucarie(boolean scumpa) {
     return new LegoGame();
   }
}

class LovingParent {
   private final Child child;

   public LovingParent(Child child) {
      this.child = child;
   }

   public void finishWorkExhausted() {
      JocCuCuburi joc = MagazinDeJucarii.cumparaJucarie(false); // sotia observa ca Lego a costat 30 EUR
      child.play(joc);
      child.noticeAndKillParent();
   }
}
class ToyShop {

}

class Child {

   public void noticeAndKillParent() {
      System.err.println("Drain parent blood");
      exit(-1);
   }

   public void play(JocCuCuburi joc) {
      System.out.println("Ma joc cu " + joc);
      ThreadUtils.sleepq(5000);
   }
}
