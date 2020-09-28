package victor.training.oo.creational.factory;

import lombok.SneakyThrows;
import victor.training.oo.stuff.ThreadUtils;

import java.math.BigDecimal;

import static java.lang.System.exit;

public class FactoriesStart {
   public static void main(String[] args) {
      LovingParent parent = new LovingParent(new Child());

      parent.finishWorkExhausted();
   }
}

interface Jucarie {

}
class Barbie implements Jucarie {
}
class Lego implements Jucarie {
}

class Papusa implements Jucarie {
   private final int stocRamas;
   private final BigDecimal pret;

   Papusa(int stocRamas, BigDecimal pret) {
      this.stocRamas = stocRamas;
      this.pret = pret;
   }
}
class MagazinDeJucarii {
   static int legoStock = 1;

   public static Jucarie cumparaJucarie(boolean scumpa) {
      if (scumpa) {
         if (legoStock > 0) {
            return new Lego();
         } else {
            return new Barbie();
         }
      } else {
         return new Papusa(1, BigDecimal.ONE);
      }
   }

   @SneakyThrows
   public static <T extends Jucarie> T cumpara(Class<T> tip) {
//      if (am deja creat singletonul, i-l dau pe cel deja existent), altfel:
      T jucarie = tip.newInstance();
      return jucarie;
   }
}

class LovingParent {
   private final Child child;

   public LovingParent(Child child) {
      this.child = child;
   }
   //exsanguinare = a scurge pana la ultima picatura de sange
   public void finishWorkExhausted() {
      Jucarie jucarie = MagazinDeJucarii.cumparaJucarie(false); // sotia observa ca Lego a costat 30 EUR
//      Lego lego = MagazinDeJucarii.cumpara(Lego.class);
      child.play(jucarie);
      // fac un dus
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

   public void play(Jucarie jucarie) {
      System.out.println("Ma joc cu " + jucarie);
      ThreadUtils.sleepq(5000);
   }
}
