package victor.training.patterns.creational;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FactoryUPB {

   public static void main(String[] args) throws IOException {

//      Writer writer = new FileWriter("a.txt");
//      Writer writer2 = new BufferedWriter(writer);

      List<String> prezenta = new ArrayList<>();
      prezenta.add("maria");
      prezenta.add("ioana");
      prezenta.add("iulia");
      prezenta.add("carmen");

      // Factory Method < o met statica care ascunde din fata ta ce tip concret primesti inapoi
      // NU vezi, nu-ti pasa. E Lista, da. una in care oric eadd, remove incerci sa faci primesti o exceptie

      // chemand o metoda vs a face NEW: te lasa sa nu-ti pese ce tip efectiv primesti inapoi.
      trimite(Collections.unmodifiableList(prezenta));

      System.out.println(prezenta);

   }

   private static void trimite(List<String> strings) {
      strings.add("ion");
   }
}
