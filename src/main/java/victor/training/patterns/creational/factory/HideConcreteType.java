package victor.training.patterns.creational.factory;

import javax.xml.parsers.DocumentBuilderFactory;

public class HideConcreteType {
   public static void main(String[] args) {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//      DocumentBuilder db = factory.newDocumentBuilder();
   }
}
