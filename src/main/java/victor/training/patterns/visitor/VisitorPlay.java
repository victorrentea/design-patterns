package victor.training.patterns.visitor;

import victor.training.patterns.visitor.model.Circle;
import victor.training.patterns.visitor.model.Shape;
import victor.training.patterns.visitor.model.Square;

import java.util.Arrays;
import java.util.List;

public class VisitorPlay {

	public static void main(String[] args) {
		List<Shape> shapes = Arrays.asList(
			new Square(10),
			new Circle(5),
			new Square(5));

		TextualRepresentationVisitor visitor = new TextualRepresentationVisitor();
		for (Shape shape : shapes) {
			shape.accept(visitor);
		}
		System.out.println("Total perimeter: " + visitor.getText());


		System.out.println("Total area: " + 0); // TODO

	}

}
