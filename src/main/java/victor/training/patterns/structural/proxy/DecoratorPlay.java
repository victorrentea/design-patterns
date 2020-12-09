package victor.training.patterns.structural.proxy;

public class DecoratorPlay {
   public static void main(String[] args) {
      IMate mate = new Mate();
      mate = new Decorator(mate);
      client(mate);
   }

   // vreau sa intervin in calea apelului de metoda, fara ca clientul sa se prinda
   public static void client(IMate mate) {
      System.out.println(mate.sum(1, 1));
   }
}

interface IMate {
   int sum(int a, int b);
}
class Decorator implements IMate {
   private final IMate mate;
   public Decorator(IMate mate) {
      this.mate = mate;
   }
   @Override
   public int sum(int a, int b) {
      System.out.println("Calling sum: " + a + "  +  " + b);
      long t0 = System.currentTimeMillis();
      int result = mate.sum(a, b);
      long t1 = System.currentTimeMillis();
      System.out.println("Ce masor : " +(t1-t0));
      return result;
   }
}
class Mate implements IMate {
   public int sum(int a, int b) {
      return a + b;
   }
}