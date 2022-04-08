package victor.training.patterns.factory.abstractfactory.spi;

public interface BlockFactory {
	Board createBoard();
	Cube createCube();
}
