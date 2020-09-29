package victor.training.patterns.creational.abstractfactory.spi;

public interface Cube {
	void stackOnto(Cube anotherCube);
	void putOn(Board board);
}
