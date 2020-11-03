package victor.training.patterns.creational.factory.abstractfactory.spi;

public interface BlockFactory {
	Board createBoard();
	Cube createCube();
}
