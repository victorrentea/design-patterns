package victor.training.oo.creational.factory.blocks;

import victor.training.oo.creational.factory.blocks.lego.LegoFactory;
import victor.training.oo.creational.factory.blocks.megablock.MegaBlockFactory;
import victor.training.oo.creational.factory.blocks.spi.BlockFactory;
import victor.training.oo.creational.factory.blocks.spi.Cube;

public class LovingParent {

	public static final Child childOne = new Child();
	
	public static void main(String[] args) {
		System.out.println("Got back from work...");
//		BlockFactory factory = new LegoFactory(); //120 lei
		BlockFactory factory = new MegaBlockFactory(); //40 lei
		System.out.println("Brought a present: " + factory);
		System.out.println("Hm....");
		childOne.playWith(factory);
		System.out.println("Good Night!");


		Cube legoCube = new LegoFactory().createCube();
		Cube megaCube = new MegaBlockFactory().createCube();
		legoCube.stackOnto(megaCube);


//		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//		DocumentBuilder db = dbf.newDocumentBuilder();
//		Document doc = db.newDocument();
//			Attr attribute = doc.createAttribute();
//		Element element = doc.createElement();
	}
}
