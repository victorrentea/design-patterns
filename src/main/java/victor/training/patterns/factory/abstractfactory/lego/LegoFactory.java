package victor.training.patterns.factory.abstractfactory.lego;

import victor.training.patterns.factory.abstractfactory.spi.BlockFactory;
import victor.training.patterns.factory.abstractfactory.spi.Board;
import victor.training.patterns.factory.abstractfactory.spi.Cube;

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
