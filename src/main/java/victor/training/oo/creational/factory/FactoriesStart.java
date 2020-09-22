package victor.training.oo.creational.factory;

import victor.training.oo.stuff.ThreadUtils;

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

   public void play() {
      ThreadUtils.sleepq(100);
      System.out.println("Kid to young, unable to play by himself. Try a toy!");
   }
}
