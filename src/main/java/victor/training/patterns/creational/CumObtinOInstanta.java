package victor.training.patterns.creational;

import org.springframework.beans.factory.annotation.Autowired;

public class CumObtinOInstanta {
}


class ServiceLocator {

   public static <T> T getInstance(Class<T> bClass) {
      return null;
   }
}

class A {
   //   @EJB
   @Autowired
//   @Inject
       B b;

   public void method() {
//      B b = ServiceLocator.getInstance(B.class);

      int x = b.metoda();
   }
}

class B {
   private final C c;

   B(C c) {
      this.c = c;
   }

   public int metoda() {
      return 0;
   }
}

class C {
}