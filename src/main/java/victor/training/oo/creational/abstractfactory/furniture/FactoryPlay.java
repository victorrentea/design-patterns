package victor.training.oo.creational.abstractfactory.furniture;

import victor.training.oo.creational.abstractfactory.furniture.spi.FurnitureFactory;
import victor.training.oo.creational.abstractfactory.furniture.valeni.ValeniFurnitureFactory;

import javax.xml.parsers.DocumentBuilderFactory;
import java.util.List;

public class FactoryPlay {

	public static void main(String[] args) {
		FurnitureFactory factory = new ValeniFurnitureFactory();

		Distributor distributor = new Distributor(factory);

		List<Object> kitchen = distributor.getKitchenFurniture();

		System.out.println("Delivered objects: " + kitchen);

		// 2
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
	}
}
