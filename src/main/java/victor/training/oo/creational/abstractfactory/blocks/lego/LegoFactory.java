package victor.training.oo.creational.abstractfactory.blocks.lego;

import victor.training.oo.creational.abstractfactory.blocks.spi.BlockFactory;
import victor.training.oo.creational.abstractfactory.blocks.spi.Board;
import victor.training.oo.creational.abstractfactory.blocks.spi.Cube;

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
