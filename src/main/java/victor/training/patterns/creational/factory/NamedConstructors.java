package victor.training.patterns.creational.factory;

public class NamedConstructors {
   public static void main(String[] args) {
      OutputFile outputFile = new OutputFile("irs");
   }
}


class OutputFile {
   private String delimiter = ";";
   private String fileExt = ".csv";
   private final String fileName;

   public OutputFile(String fileName) {
      this.fileName = fileName;
   }

   public void writeStuff(String stuff) {
      System.out.println("Writing stuff using " + delimiter + " in the file " + fileName + fileExt);
   }
}
