package victor.training.patterns.creational.factory;

public class NamedConstructors {
   public static void main(String[] args) {


      Long l1 = Long.valueOf(127L);
      Long l2 = Long.valueOf(127L);

      System.out.println(l1 == l2);

      OutputFile outputFile = OutputFile.createCsv("irs");
   }
}






class OutputFile {
   private final String delimiter;
   private final String fileExt;
   private final String fileName;

   public static OutputFile createCsv(String fileName) {
      return new OutputFile(fileName, ";", ".csv");
   }

   public OutputFile(String fileName, String delimiter, String fileExt) {
      this.delimiter = delimiter;
      this.fileExt = fileExt;
      this.fileName = fileName;
   }

   public void writeStuff(String stuff) {
      System.out.println("Writing stuff using " + delimiter + " in the file " + fileName + fileExt);
   }
}
