package victor.training.patterns.creational.factory.abstractfactory.lego;

import victor.training.patterns.creational.factory.abstractfactory.spi.BlockFactory;
import victor.training.patterns.creational.factory.abstractfactory.spi.Board;
import victor.training.patterns.creational.factory.abstractfactory.spi.Cube;

public class LegoBox implements BlockFactory {

	@Override
	public Board createBoard() {
		return new LegoBoard();
	}

	@Override
	public Cube createCube() {
		return new LegoCube();
	}

}
