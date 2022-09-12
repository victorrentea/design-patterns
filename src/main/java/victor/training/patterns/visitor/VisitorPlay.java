package victor.training.patterns.visitor;

import victor.training.patterns.visitor.model.Circle;
import victor.training.patterns.visitor.model.Shape;
import victor.training.patterns.visitor.model.Square;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VisitorPlay {

	public static void main(String[] args) {
		List<Shape> shapes = Arrays.asList(
			new Square(10),
			new Circle(5),
			new Square(5));

		/**/List<Textualizable> wrappere = new ArrayList<>();
//		for (Shape shape : shapes) {
//		switch() + sealed classes
//			if (shape instanceof Square) {
//				wrappere.add(new SquareWrapper((Square) shape));
//			} else if (shape instanceof Circle) {
//				wrappere.add(new CircleWrapper((Circle) shape));
//			} else {
//				throw new IllegalArgumentException("Valeu! nu stiam de tipu asta " + shape);
//			}
//		}

		AsTextVisitor visitor = new AsTextVisitor();
		for (Shape shape : shapes) {
			shape.accept(visitor);
		}
		System.out.println(visitor.getTexts());

		System.out.println("Total area: " + 0); // TODO
	}

	public ShapeWrapper wrap(Shape shape) {
		if (shape instanceof Circle) {
			return new CircleWrapper((Circle) shape);
		}
		throw new IllegalArgumentException();
	}
}

interface Textualizable {
	String asText();
}
abstract class ShapeWrapper {

}
class SquareWrapper extends ShapeWrapper implements Textualizable{
	private final Square square;

	SquareWrapper(Square square) {
		this.square = square;
	}

	public String asText() {
		return "Patrat cu latura=" + square.getEdge();
	}
}
class CircleWrapper extends ShapeWrapper implements Textualizable{
	private final Circle circle;

	CircleWrapper(Circle circle) {
		this.circle = circle;
	}

	public String asText() {
		return "Cerc cu radius=" + circle.getRadius();
	}
}