package victor.training.patterns.creational;

public class CePatternEAsta {

   public static void main(String[] args) {
      new X().methodX();
      new Y().methodY();
   }

}

// ancient-school Singleton Pattern (programmatic style) - singura optiune daca nu ai un COntainer de Dep Injection (spring, EJB, Guice)
//@Component // Spring garanteaza ca va creea o singura INSTANTA din aceasta clasa S
//@Service
class S {
   private static S INSTANCE;

   private S() {
   }

   public static S getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new S(); // lazy singleton
      }
      return INSTANCE;
   }

   public void deInstanta() {
      System.out.println(this);
   }
}


class X {
   public void methodX() {
      S.getInstance().deInstanta();
      S.getInstance().deInstanta();
      S.getInstance().deInstanta();
   }
}

class Y {
   public void methodY() {
      S.getInstance().deInstanta();
   }
}