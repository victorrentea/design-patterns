package victor.training.patterns.structural.proxy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class DecoratorPlay {

   public static void main(String[] args) throws IOException {
      Matematica matematica = new MatematicaDecoratorCuLog(new MatematicaImpl());
//      Matematica matematica =  new MatematicaImpl() ;

      Writer writer = new BufferedWriter(new FileWriter("a.txt")); // decorator din JDK
//      Writer writer = new FileWriter("a.txt");

      System.out.println(matematica.suma(1, 2));
      System.out.println(matematica.suma(1, 3));
      System.out.println(matematica.produs(2, 3));
   }

   static class MatematicaDecoratorCuLog implements Matematica {
      private final Matematica delegate;

      MatematicaDecoratorCuLog(Matematica delegate) {
         this.delegate = delegate;
      }

      @Override
      public int suma(int a, int b) {
         System.out.println("Suma " + a + " cu " + b);
         return delegate.suma(a, b);
      }

      @Override
      public int produs(int a, int b) {
         System.out.println("Produs " + a + " cu " + b);
         return delegate.produs(a, b);
      }
   }

   static class MatematicaImpl implements Matematica {

      @Override
      public int suma(int a, int b) {
         return a + b;
      }

      @Override
      public int produs(int a, int b) {
         return a * b;
      }
   }

   interface Matematica {
      int suma(int a, int b);

      int produs(int a, int b);
   }
}

