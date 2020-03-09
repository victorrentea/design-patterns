package victor.training.oo.creational.abstractfactory.blocks;

import victor.training.oo.creational.abstractfactory.blocks.lego.LegoFactory;
import victor.training.oo.creational.abstractfactory.blocks.spi.BlockFactory;

public class LovingParent {

	public static final Child childOne = new Child();
	
	public static void main(String[] args) {
		System.out.println("Got back from work...");
		BlockFactory factory = new LegoFactory();
		System.out.println("Brought a present: " + factory);
		System.out.println("Hm....");
		childOne.playWith(factory);
		System.out.println("Good Night!");
	}
}
