package victor.training.patterns.creational.factory;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class XmlPlay {
   public static void main(String[] args) throws ParserConfigurationException {

      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

      System.out.println(dbf);

      DocumentBuilder documentBuilder = dbf.newDocumentBuilder();


//      DataSource ds; // JDBC Connection Pool
//      Connection connection = ds.getConnection(); // limiteaza cate obiecte active exista la un moment dat.
//      connection.close(); // elibereaza conexiunea si alt request poate lua aceasta conex si s-o refoloseasca din pool (liina precedenta)


   }
}
