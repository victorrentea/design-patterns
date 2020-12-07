package victor.training.patterns.creational.factory.abstractfactory;

import victor.training.patterns.creational.factory.abstractfactory.lego.LegoBox;
import victor.training.patterns.creational.factory.abstractfactory.mega.MegaBlocksBox;
import victor.training.patterns.creational.factory.abstractfactory.spi.BlockFactory;
import victor.training.patterns.creational.factory.abstractfactory.spi.Cube;

public class LovingParent {

	public static final Child childOne = new Child();

	public static void main(String[] args) {
		System.out.println("Got back from work... ");
		BlockFactory box = new MegaBlocksBox();
		System.out.println("Brought a present: " + box);
		System.out.println("Hm....");
		childOne.playWith(box);
		System.out.println("Good Night!");


		Cube legoCube = new LegoBox().createCube();
		Cube megaCube = new MegaBlocksBox().createCube();

		legoCube.stackOnto(megaCube);


	}
}
