package victor.training.oo.creational.servicelocator;

import victor.training.oo.stuff.ThreadUtils;

import java.util.HashMap;
import java.util.Map;

public class ServiceLocatorPlay {
   public static void main(String[] args) {
      B.getInstance().getDateScumpeDinBaza(); // foarte usor sa obtii instanta de B de **oriunde**
      new A().m();
   }
}

// OOP zice sa tii logica langa date. --> sa pui toata logica de domeniu in @Entity. HORROR in viata reala. --> Domain-Driven Design

// In app enterprise tipic se separa: (adica exact pe dos cum zice OOP)
// 1 - clasele de date @Entity
// 2 - clasele cu logica @Service

// un design pur "separate logic from data" - zice ca nu tre sa ai date de loc in clasele cu logica.
// --> deci neavand date in ele, o singura instanta este suficienta --> Singleton

class ServiceLocator {
   static Map<Class<?>, Object> registry = new HashMap<>();
   static {
      registry.put(C.class, new C());
      // din teste, puteai hackui, suprascrie, sa decorezi
      registry.put(C.class, new C() {
         @Override
         public void p() {
            //fake
         }
      });
   }
   public static C createC() {
      return (C) registry.get(C.class);
   }
}

class A2{
//   B b = ServiceLocator.createB();

}
class A {

   private B b = B.getInstance();
   public void m() {
//      b.setC(new C()); // temporal coupling. periculos ca poti sa uiti/sa
//      D d = new D();
//      d.setDA(new DA());
//      b.setD(d);
      // nu stii ca trebuie chemata inainte de n()

      // 300 linii de cod
      String s = b.n();
      System.out.println(s);
   }
//      BEAger beAger;
   public void m2() {
//      B b = new B(new C(), new D(new DA()), new E());
//      B b = B.getInstance();
      String s = b.n();
      System.out.println(s);
   }
}

class BEager {
   public final static BEager INSTANCE = new BEager();
   private BEager() {
      System.out.println("Eager singleton: constr ruleaza la class load (adica prima data cand referi numele clasei de undeva)");
      System.out.println("Problema: e posibil sa ruleze prea devreme.");

//      jdbc.query(...) posibil sa crape pentru ca inca nu s-a incarcat si initializat coresp conex cu DB
   }
}

class B {
   private String dateScumpeDinBaza;
   private B() {
      System.out.println("Constr ruleaza lazy la primul apel al lui getInstance");
      System.out.println("Calling DB");
      dateScumpeDinBaza = "din DB";
      ThreadUtils.sleep(2000);
      System.out.println("results back");
   }

   public String getDateScumpeDinBaza() {
      return dateScumpeDinBaza;
   }

   //   private final static B b = new B();

   private static B INSTANCE;
   public  static B getInstance() {
      // https://en.wikipedia.org/wiki/Double-checked_locking
      if (INSTANCE != null) {
         return INSTANCE;
      }
      synchronized (B.class) {
         if (INSTANCE == null) {
            INSTANCE = new B();
         }
         return INSTANCE;
      }
   }

   private C c = ServiceLocator.createC(); /*= new C()*/    /* similar cu = C.getInstance()*/;
//   private D d;
//   private E e;
//   public B(C c) {
//      this.c = c;
//   }
//   void setC(C c) {
//      this.c = c;
//   }
   public String n() {
      // 300 linii de cod
      c.p();
      return "x";
   }

   public synchronized void reportPerson(String person) {
      //call WS exactly once in parallel
   }
}

class C {
   public void p() {

   }

}