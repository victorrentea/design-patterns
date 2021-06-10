package victor.training.patterns.creational.factory;

public class NamedConstructors {
   public static void main(String[] args) {
      OutputFile outputFile = OutputFile.semicolonDelimited("irs");
      OutputFile outputFile8 = OutputFile.semicolonDelimited("irs");
      OutputFile outputFile7 = OutputFile.semicolonDelimited("irs");
      OutputFile outputFile6 = OutputFile.semicolonDelimited("irs");
      OutputFile outputFile5 = OutputFile.semicolonDelimited("irs");
      OutputFile outputFile4 = OutputFile.semicolonDelimited("irs");


      // special case
      OutputFile outputFile2 = OutputFile.pipeDelimited("irs");

      //x - special case
      OutputFile outputFile43 = OutputFile.withExtension("irs", ".txt");
   }
}


class OutputFile {
   private String delimiter; //  |
   private String fileExt;
   private final String fileName;

   //   public OutputFile(String fileName) {
//      this(fileName, ";");
//   }
   private OutputFile(String fileName, String delimiter, String fileExt) {
      this.fileName = fileName;
      this.delimiter = delimiter;
      this.fileExt = fileExt;
   }

   public static OutputFile semicolonDelimited(String fileName) {
      return new OutputFile(fileName, ";", ".csv");
   }

   public static OutputFile pipeDelimited(String fileName) {
      return new OutputFile(fileName, "|", ".csv");
   }

   public static OutputFile withExtension(String fileName, String extension) {
      return new OutputFile(fileName, ";", extension);
   }

   public void writeStuff(String stuff) {
      System.out.println("Writing stuff using " + delimiter + " in the file " + fileName + fileExt);
   }
}
