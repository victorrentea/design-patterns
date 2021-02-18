package victor.training.patterns.creational;

import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServiceLocatorNeed {
   public static void main(String[] args) {
//      new A().method(new OSW);
   }
}


class A {

   private final B b;

   public A(B b) {
      this.b = b;
   }

   public void method(Writer writer) {
      // logica
      /*ServiceLocator.getInstance(B.class)*/
      List<String> list = new ArrayList<>();

//      list = new LinkedList<>();

      b.functie();
      // logica
   }
}

class ServiceLocator {

   private static HashMap<Object, Object> mocks = new HashMap<>();

   public static <T> T getInstance(Class<T> clazz) {
      try {
         if (mocks.containsKey(clazz)) {
            return (T) mocks.get(clazz);
         }
         return clazz.newInstance();
      } catch (InstantiationException | IllegalAccessException e) {
         throw new RuntimeException(e);
      }
   }
}

//@Scope("singleton")
class B {

//   private static B INSTANCE; // Singeton: sa garanteze ca se creaza o singura instanta PER MASINA
//   private B() {}
//
//   public static B getInstance() {
//      if (INSTANCE == null) {
//         INSTANCE = new B();
//      }
//      return INSTANCE;
//   }

   public int functie() {
      return 0;
   }
}