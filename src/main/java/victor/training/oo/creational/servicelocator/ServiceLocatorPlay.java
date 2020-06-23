package victor.training.oo.creational.servicelocator;

public class ServiceLocatorPlay {
   public static void main(String[] args) {
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
//   public static B createB() {
//      return new B(new C()/*, new D(new DA()), new E()*/);
//   }
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
   public void m2() {
//      B b = new B(new C(), new D(new DA()), new E());
//      B b = B.getInstance();
      String s = b.n();
      System.out.println(s);
   }
}

class B {
   private B() {}
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

   private C c /*= new C()*/    /* similar cu = C.getInstance()*/;
//   private D d;
//   private E e;
//   public B(C c) {
//      this.c = c;
//   }
   void setC(C c) {
      this.c = c;
   }
   public String n() {
      // 300 linii de cod
      c.p();
      return "x";
   }

}

class C {
   public void p() {

   }

}