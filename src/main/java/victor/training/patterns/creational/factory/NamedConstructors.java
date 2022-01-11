package victor.training.patterns.creational.factory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class NamedConstructors {
   public static void main(String[] args) throws ParserConfigurationException {
      OutputFile outputFile = OutputFile.aCsv("irs");

      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance(); // XML cu DOM
      Document doc = dbf.newDocumentBuilder().newDocument();

      Element userTag = doc.createElement("user"); // de fapt la runtime primesti instanta de ElementImpl din lib (eg xerces)  // abstract factory
      Attr attr = doc.createAttribute("id");// de fapt la runtime primesti instanta de AttrImpl din lib (eg xerces)

//      ((AttrImpl)attr)
//      dom4j

      userTag.setAttributeNode(attr);


//      DataSource ds = oracle
//      Connection connection1 = ds.getConnection();
//      PreparedStatement ps = connection1.prepareStatement();
//
//      DataSource ds2 = pgDS
//      Connection connection2 = ds2.getConnection();
//      .


   }
}


//class OutputFileFactory
//class CsvOutoutFile extends OutputFile{
//}
class OutputFile {
   private String fileExt;
   private final String fileName;

   public OutputFile(String fileName, String fileExt) {
      this.fileName = fileName;
      this.fileExt = fileExt;
   }

//   public OutputFile(String fileName) {
//      this(fileName, ".csv");
//   }

   public static OutputFile aCsv(String fileName) {
      return new OutputFile(fileName, ".csv");
   }

   public void writeStuff(String stuff) {
      System.out.println("Writing stuff in the file " + fileName + fileExt);
   }
}


// cand as mai vrea sa obtin o instanta nu prin NEW ci prin apel de fct statica
//


class ObiectDeCreat {

}
