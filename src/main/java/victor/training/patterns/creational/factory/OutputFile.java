package victor.training.patterns.creational.factory;

public class OutputFile {
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
