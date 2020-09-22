package victor.training.oo.structural.proxy;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class DecoratorIO {
   public static void main(String[] args) throws IOException {
      Writer writer = new FileWriter("out.txt");
      writer = new BufferedWriter(writer);
      writer.write("sss");
   }
}
