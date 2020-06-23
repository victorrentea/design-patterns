package victor.training.oo.creational.abstractfactory.blocks;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import victor.training.oo.creational.abstractfactory.blocks.lego.LegoFactory;
import victor.training.oo.creational.abstractfactory.blocks.megablock.MegaBlockBox;
import victor.training.oo.creational.abstractfactory.blocks.spi.BlockFactory;
import victor.training.oo.creational.abstractfactory.blocks.spi.Cube;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class LovingParent {

	public static final Child childOne = new Child();
	
	public static void main(String[] args) throws ParserConfigurationException {
		System.out.println("Got back from work...");
		BlockFactory factory = new MegaBlockBox();
		System.out.println("Brought a present: " + factory);
		System.out.println("Hm....");
		childOne.playWith(factory);
		System.out.println("Good Night!");


		Cube legoCube = new LegoFactory().createCube();
		Cube megaCube = new MegaBlockBox().createCube();

		legoCube.stackOnto(megaCube);

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder = dbf.newDocumentBuilder();

		Document doc = documentBuilder.newDocument();


		Element childOne = doc.createElement("e");
		Attr attr = doc.createAttribute("id");

		childOne.appendChild(attr);

	}
}
