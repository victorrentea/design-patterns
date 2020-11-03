package victor.training.patterns.creational.factory.abstractfactory.spi;

public interface Cube {
	void stackOnto(Cube anotherCube);
	void putOn(Board board);
}
