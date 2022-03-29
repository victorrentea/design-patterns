package victor.training.patterns.creational.factory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilderFactory;
import java.lang.ref.WeakReference;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.concurrent.ConcurrentHashMap;

public class NamedConstructors {
   public static void main(String[] args) {
//      OutputFile outputFile = new OutputFile("irs"); // no clue I get a '.csv', yet.
//      OutputFile outputFile = OutputFile.aCsv("irs");

//      DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
//      Document document = documentBuilderFactory.newDocumentBuilder().newDocument();
//      Element element = document.createElement();
//      Attr attribute = document.createAttribute();
//      element.appendChild(attribute);
//
//      // Abstract factory = you find an interface creating interfaces !:!:!:!""!"!??!@$*^&&^!!%!&&!@$9
//      DataSource connectionPool;
//      Connection abstractFactory = new PGConnection();
//
//      PreparedStatement product1 = abstractFactory.prepareStatement("s");
//      Blob product2 = abstractFactory.createBlob();
//
//      product1.setBlob(1, product2);
      // BLOCKS if all the 20 conn in a connection pool are current in use.

//      connection.close();
      Long aLong = Long.valueOf(127);
      Long aLong2 = Long.valueOf(127);

      String s = "aa" + args;
      s = s.intern();

      System.out.println(aLong == aLong2);
   }


}

class CustomerId {
   private final long id;

   private CustomerId(long id) {
      this.id = id;
   }

   private static final ConcurrentHashMap<Long, WeakReference<CustomerId>> inMemoryNow = new ConcurrentHashMap<>();
   public void of(Long id) {
//      return
//if there is a custId in mem right now, use it. if not, new > put in map.
   }
}


class OutputFile {
   private final String fileExt;
   private final String fileName;

   public static OutputFile aCsv(String fileName) {
      return new OutputFile(fileName, ".csv");
   }
//   public OutputFile(String fileName) {
//      this(fileName, ".csv"); // TODO make apparent that it's a 'csv' being created by default
//   }

   public OutputFile(String fileName, String fileExt) { // canonical constructor. All overloads delegate to it
      this.fileName = fileName;
      this.fileExt = fileExt;
   }

   public void writeStuff(String stuff) {
      System.out.println("Writing stuff in the file " + fileName + fileExt);
   }
}
