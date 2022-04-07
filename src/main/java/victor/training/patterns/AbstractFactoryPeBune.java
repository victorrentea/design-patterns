package victor.training.patterns;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.sql.DataSource;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AbstractFactoryPeBune {
   public static void main(String[] args) throws SQLException {


      DataSource ds = null;
      Connection factory = ds.getConnection(); // nu stii ce impl de connection primesti JDBC, PG, H2 . codu arata la fel


      PreparedStatement ps = factory.prepareStatement("a");

      Blob blob = factory.createBlob();
      ps.setBlob(1, blob);
      ;


//      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//      DocumentBuilder db = dbf.newDocumentBuilder();
//      Document d = db.newDocument();
//
//
//      Element element = d.createElement();
//      element.setAttributeNode(d.createAttribute())

   }
}
