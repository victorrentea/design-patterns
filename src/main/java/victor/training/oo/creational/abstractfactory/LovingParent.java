package victor.training.oo.creational.abstractfactory;

import victor.training.oo.creational.abstractfactory.lego.LegoFactory;
import victor.training.oo.creational.abstractfactory.spi.BlockFactory;

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
