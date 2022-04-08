package victor.training.patterns.factory;

public class NamedConstructors {
   public static void main(String[] args) {
      OutputFile outputFile = new OutputFile("irs"); // no clue I get a '.csv', yet.
   }
}


class OutputFile {
   private final String fileExt;
   private final String fileName;

   public OutputFile(String fileName) {
      this(fileName, ".csv"); // TODO make apparent that it's a 'csv' being created by default
   }

   public OutputFile(String fileName, String fileExt) { // canonical constructor. All overloads delegate to it
      this.fileName = fileName;
      this.fileExt = fileExt;
   }

   public void writeStuff(String stuff) {
      System.out.println("Writing stuff in the file " + fileName + fileExt);
   }
}
