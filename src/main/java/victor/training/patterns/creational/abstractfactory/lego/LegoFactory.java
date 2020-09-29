package victor.training.patterns.creational.abstractfactory.lego;

import victor.training.patterns.creational.abstractfactory.spi.BlockFactory;
import victor.training.patterns.creational.abstractfactory.spi.Board;
import victor.training.patterns.creational.abstractfactory.spi.Cube;

public class LegoFactory implements BlockFactory {

	@Override
	public Board createBoard() {
		return new LegoBoard();
	}

	@Override
	public Cube createCube() {
		return new LegoCube();
	}

}
