package victor.training.patterns.creational.factory.abstractfactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class ToateFactoryurile {

   public static void main(String[] args) throws ParserConfigurationException {
      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

      DocumentBuilder db = dbf.newDocumentBuilder();

      Document doc = db.newDocument();

      Element div = doc.createElement("div");
      Attr attr = doc.createAttribute("id");

      div.appendChild(attr);
   }


   // <div id="aa" > aa</div>
}
