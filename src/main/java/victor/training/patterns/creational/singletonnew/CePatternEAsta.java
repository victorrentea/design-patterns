package victor.training.patterns.creational.singletonnew;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

@SpringBootApplication
public class CePatternEAsta implements CommandLineRunner {
   @Autowired
   private X x;
   @Autowired
   private Y y;

   public static void main(String[] args) {
      SpringApplication.run(CePatternEAsta.class, args);
   }

   @Override
   public void run(String... args) throws Exception {
      x.methodX();
      y.methodY();
   }

}

@Component
class T {
}

@Component // Spring garanteaza ca va creea o singura INSTANTA din aceasta clasa S
class S {
   @Autowired
   T t;

   public int deInstanta() {
      System.out.println(this);
      return (int) System.currentTimeMillis();
   }
}

@Component
class X {
   @Autowired
   private S s;

   public int methodX() {
//      S s = new S();
//      S s = ServiceLocator.getInstance(S.class); // Tip: folosesti un Map<class,instanta>
      System.out.println("Ce mi-a fost dat:  " + s);
      return s.deInstanta() + 1;
   }
}

@Component
class Y {
   @Autowired
   private S s;

   public void methodY() {
      System.out.println("Ce mi-a fost dat:  " + s);
      s.deInstanta();
   }
}