package victor.training.patterns.creational;

class ServiceLocator {
   //Map<Class, Obect>
   public static <T> T getBean(Class<T> xClass) {
      return null;
   }
}

public class DeUndeVinInstantele {
   //   @Inject / @Autowired
   private X x;

   public void method() {

//      X x = new X(new Y(new Z())); // asta!!
//      X x = ServiceLocator.getBean(X.class); // intoarce un mock in teste
//      complexitate
      x.methodComplexa();

//      DataSource ds;
//      Connection c = ds.getConnection();// 1 din cele 20 de conn global din app ta
//      c.close();


   }

}

class X {
   private final Y y;

   X(Y y) {
      this.y = y;
   }

   public void methodComplexa() { // 23 teste JUNit
//      complexitate
//      complexitate
//      complexitate
//      complexitate
//      complexitate
//      complexitate
   }
}

class Y {
}
