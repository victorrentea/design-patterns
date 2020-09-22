package victor.training.oo.creational.abstractfactory.spi;

public interface Cube {
	void stackOnto(Cube anotherCube);
	void putOn(Board board);
}
