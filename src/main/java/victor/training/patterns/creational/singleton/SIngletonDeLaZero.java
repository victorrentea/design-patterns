package victor.training.patterns.creational.singleton;

class CodClient {
//   public void method() {
//      SingletonAnii90Supplier supplier = new SingletonAnii90Supplier();
//      SingletonAnii90BisSupplier supplierBis = new SingletonAnii90BisSupplier();
//      new SIngletonDeLaZero(supplier,supplierBis);
//      altaMetoda(supplier, supplierBis);
//   }
//
//   private void altaMetoda(SingletonAnii90Supplier supplier, SingletonAnii90BisSupplier supplierBis) {
//      new SIngletonDeLaZero(supplier, supplierBis);
//   }
}

public class SIngletonDeLaZero {

//   @Autowired
//   SingletonAnii90 unica;

   private final SingletonAnii90Supplier supplier;

   public SIngletonDeLaZero(SingletonAnii90Supplier supplier) {
      this.supplier = supplier;
   }
//   @Inject
//   SingletonAnii90 unica;

   public void method() {
//      SingletonAnii90 unica = SingletonAnii90.getInstance(); // getInstance (!STATICA!) trebuie sa intoarca mockul !

//      SingletonAnii90Supplier supplier = new SingletonAnii90Supplier() // de unde ?;
      SingletonAnii90 unica = supplier.createSingletonAnii90();

      unica.met(); // mockuiesti CUM?!?!!!?!?! puii mei strecor aici in variabla 'unica' un mock!?!
   }
}

class SingletonAnii90Supplier {
   private SingletonAnii90 instanta;

   public SingletonAnii90 createSingletonAnii90() {
      if (instanta == null) {
         instanta = new SingletonAnii90();
      }
      return instanta;
   }
}

class NostalgiaPascal {
   public static int global; // joac-o p'asta
}

//@Service
class SingletonAnii90 {
   private static SingletonAnii90 INSTANCE;// = new SingletonAnii90();
   //   class OneEnum {
//      ONE(new SingletonAnii90())
//   }
   private String field;// fara asta nu are sens singleton

   SingletonAnii90() {
      System.out.println("Acum ma creez iau de prin baza");
   }

   public synchronized static SingletonAnii90 getInstance() {
      if (INSTANCE == null) {
         INSTANCE = new SingletonAnii90();
      }
      return INSTANCE;
   }

   public void met() {
      //logica
   }
   // DE CE singleton? ca sa shareuiesti date (campuri) in aplicatie

   public String getField() {
      return field;
   }

   public void setField(String field) {
      this.field = field;
   }
}

// cateva exemple practice de cazuri cand AI NEVOIE de date globale comune
// > connection pool (mapa cu conex dispon la DB). @Claudiu: dupa un deploy mai aveam conn agatate de la deployul precedent.
// problema: Classloaderul anterior era inca in memorie: "Class loader leak" (deployam pe WebLogic)
//    - mai erau threaduri pornite in rulare la mom deployului
// sa lasi date pe threadlocaluri din Servlet thread pool

// ce diferenta este intre un camp de instanta intr-u singleton ( e unic si el ) si un camp static ? NU E .
// Singletoanele promoveaza non OOP in Java

// de ce credeti ca Singleton ca mai sus e antipattern https://stackoverflow.com/questions/137975/what-are-drawbacks-or-disadvantages-of-singleton-pattern
// - greu de traceuit
// - greu de testat!
// - non thread safe
// - Incalca SRP: ma ocup si de mgmt lifecycleului meu si de logica mea.

// cu ce-l inlocuim: cu un dep injection framework (Spring, Guice, EJB.. )
// o sa aiba grija frameworkul sa instantieze o singura data



