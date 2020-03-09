package victor.training.oo.creational.factory.furniture;

import victor.training.oo.creational.factory.furniture.spi.FurnitureFactory;

import java.util.ArrayList;
import java.util.List;

public class Distributor {

	private final FurnitureFactory factory;

	public Distributor(FurnitureFactory factory) {
		this.factory = factory;
	}

	public List<Object> getKitchenFurniture() {
		List<Object> objects = new ArrayList<>();
		objects.add(factory.createTable());
		for (int i = 0; i < 4; i++) {
			objects.add(factory.createChair());
		}
		// TODO add Stand product to the kitchen furniture
		return objects;
	}
}
