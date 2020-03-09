package victor.training.oo.creational.abstractfactory.blocks.spi;

public interface BlockFactory {
	Board createBoard();
	Cube createCube();
}
