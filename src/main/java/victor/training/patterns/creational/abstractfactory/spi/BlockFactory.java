package victor.training.patterns.creational.abstractfactory.spi;

public interface BlockFactory {
	Board createBoard();
	Cube createCube();
}
