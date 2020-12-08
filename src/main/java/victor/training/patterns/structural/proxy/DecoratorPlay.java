package victor.training.patterns.structural.proxy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class DecoratorPlay {
   public static void main(String[] args) throws IOException {
//      ICeva ceva = new Ceva();
//      ICeva ceva = new CevaPlusPlus(new Ceva());
      ICeva ceva = new Ceva();
      ceva = new CevaMinusMinus(new CevaPlusPlus(ceva));

      method(ceva);

      Writer writer = new FileWriter("f.txt");
      writer = new BufferedWriter(writer);
   }
   public static void method(ICeva ceva) {
      ceva.method();
   }
}


interface ICeva {
   void method();
}
class CevaPlusPlus implements ICeva {
   private final ICeva delegate;
   CevaPlusPlus(ICeva delegate) {
      this.delegate = delegate;
   }
   public void method() {
      System.out.println("++");
      delegate.method(); // ai adevarata
      System.out.println("++");
   }
}
class CevaMinusMinus implements ICeva {
   private final ICeva delegate;
   CevaMinusMinus(ICeva delegate) {
      this.delegate = delegate;
   }
   @Override
   public void method() {
      System.out.println("--");
      delegate.method(); // ai adevarata
      System.out.println("--");
   }
}
class Ceva implements ICeva{
   @Override
   public void method() {
      System.out.println("Ceva");
   }
}