package victor.training.patterns.creational.factory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NamedConstructors {
   public static void main(String[] args) {
//      OutputFile outputFile = new OutputFile("anaf");
      OutputFile outputFile = OutputFile.aCsv("anaf");
      OutputFile outputFile2 = OutputFile.aCsv("anaf");
      OutputFile outputFile3 = OutputFile.aCsv("anaf");
      OutputFile outputFile4 = OutputFile.aCsv("anaf");
      OutputFile outputFile5 = OutputFile.aCsv("anaf");

      OutputFile outputFileTxt = new OutputFile("anaf", ".txt");


      List<String> list = new ArrayList<>();
      list.add("a");

      List<String> listWrapped = Collections.unmodifiableList(list);

      System.out.println(listWrapped.get(0));

      listWrapped.remove(0);
   }
}


class OutputFile {
   private String delimiter = ";";
   private String fileExt;
   private final String fileName;

   public OutputFile(String fileName, String fileExt) { // canonic constructor
      this.fileName = fileName;
      this.fileExt = fileExt;
   }

   //   public OutputFile(String fileName) {
//     this(fileName, ".csv");
//   }
   public static OutputFile aCsv(String fileName) {
      return new OutputFile(fileName, ".csv");
   }

   public void writeStuff(String stuff) {
      System.out.println("Writing stuff using " + delimiter + " in the file " + fileName + fileExt);
   }
}
