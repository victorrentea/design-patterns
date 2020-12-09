package victor.training.patterns.structural.proxy;

public class DecoratorPlay {
   public static void main(String[] args) {
      Mate mate = new Mate();
      client(new DecoratorCareMasoaraTimpul(mate));
   }

   // vreau sa intervin in calea apelului de metoda, fara ca clientul sa se prinda
   public static void client(DecoratorCareMasoaraTimpul mate) {
      System.out.println(mate.sum(1, 1));
   }
}

class DecoratorCareMasoaraTimpul {
   private final Mate mate;
   public DecoratorCareMasoaraTimpul(Mate mate) {
      this.mate = mate;
   }

   public int sum(int a, int b) {
      long t0 = System.currentTimeMillis();
      int result = mate.sum(a, b);
      long t1 = System.currentTimeMillis();
      System.out.println("Ce masor : " +(t1-t0));
      return result;
   }
}
class Mate  {
   public int sum(int a, int b) {
      return a + b;
   }
}