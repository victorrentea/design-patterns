package victor.training.patterns.proxy;

public class FPVersion {

  public static void main(String[] args) {
    Maths maths = new Maths();
//    runInTransaction(()->maths.sum(1,2));
//    runOnlyFor("DOCTOR",()->maths.sum(1,2));
    time(()->maths.sum(1,2));

    time(()->maths.product(6,8));

    maths.product(8,1312);
  }

  public static void time(Runnable runnable) {
    long t0 = System.currentTimeMillis();
    runnable.run();
    long t1 = System.currentTimeMillis();
    System.out.println("Duration: " + (t1-t0) + " ms");
  }
}
