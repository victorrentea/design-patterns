package victor.training.oo.creational.abstractfactory.lego;

import victor.training.oo.creational.abstractfactory.spi.BlockFactory;
import victor.training.oo.creational.abstractfactory.spi.Board;
import victor.training.oo.creational.abstractfactory.spi.Cube;

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
