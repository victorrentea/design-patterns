package victor.training.oo.creational.abstractfactory.furniture.valeni;

import victor.training.oo.creational.abstractfactory.furniture.spi.Chair;
import victor.training.oo.creational.abstractfactory.furniture.spi.FurnitureFactory;
import victor.training.oo.creational.abstractfactory.furniture.spi.Table;

public class ValeniFurnitureFactory implements FurnitureFactory {

	@Override
	public Chair createChair() {
		return new ValeniChair();
	}

	@Override
	public Table createTable() {
		return new ValeniTable();
	}

}
