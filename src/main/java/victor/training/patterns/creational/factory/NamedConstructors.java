package victor.training.patterns.creational.factory;

public class NamedConstructors {
   public static void main(String[] args) {
      OutputFile outputFile = OutputFile.createCsv("anaf");
      OutputFile outputFile2 = new OutputFile("anaf");
   }
}


class OutputFile {
   private String delimiter;
   private String fileExt;
   private final String fileName;

   public static OutputFile createCsv(String fileName) {
      return new OutputFile(fileName, ".csv", ";");
   }
   public OutputFile(String fileName) {
      this(fileName, ".csv", ";");
   }
   public OutputFile(String fileName, String fileExt, String delimiter) { // canonic constructor
      this.fileName = fileName;
      this.fileExt = fileExt;
      this.delimiter = delimiter;
   }


   public void writeStuff(String stuff) {
      System.out.println("Writing stuff using " + delimiter + " in the file " + fileName + fileExt);
   }
}
