package victor.training.patterns.factory.abstractfactory.spi;

public interface Cube {
	void stackOnto(Cube anotherCube);
	void putOn(Board board);
}
