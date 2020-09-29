package victor.training.oo.structural.proxy;

import org.apache.commons.io.output.ProxyWriter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class DecoratorInJDK {
   public static void main(String[] args) throws IOException {
      Writer fileWriter = new FileWriter("a.txt");

      Writer bufferedWriter = new BufferedWriter(fileWriter);

      new ProxyWriter(fileWriter){
         @Override
         public void write(int idx) throws IOException {
//            totalWritten++;
            super.write(idx);
         }

         @Override
         public void write(char[] chr, int st, int end) throws IOException {
//            totalWritten+=end-st;
//            if (totalWritten > )
            super.write(chr, st, end);
         }
      };
      bufferedWriter.write("a");
   }
}
