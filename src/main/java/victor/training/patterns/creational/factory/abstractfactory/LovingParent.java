package victor.training.patterns.creational.factory.abstractfactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import victor.training.patterns.creational.factory.abstractfactory.lego.LegoFactory;
import victor.training.patterns.creational.factory.abstractfactory.mega.MegablocksFactory;
import victor.training.patterns.creational.factory.abstractfactory.spi.BlockFactory;
import victor.training.patterns.creational.factory.abstractfactory.spi.Cube;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class LovingParent {

	public static final Child childOne = new Child();

	public static void main(String[] args) throws ParserConfigurationException {
		System.out.println("Got back from work...");
		BlockFactory factory = new MegablocksFactory(); // 40 lei sheet sotia=:)
		System.out.println("Brought a present: " + factory);
		System.out.println("Hm....");
		childOne.playWith(factory);
		System.out.println("Good Night!");


		Cube legoCube = new LegoFactory().createCube();
		Cube megaCube = new MegablocksFactory().createCube();
		legoCube.stackOnto(megaCube);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();

		Document doc = db.newDocument();
		Element element = doc.createElement("a");
		Attr attr = doc.createAttribute("href");

		element.setAttributeNode(attr);
	}
}
