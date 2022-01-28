package victor.training.patterns.behavioral.visitor;

import victor.training.patterns.behavioral.visitor.model.Circle;
import victor.training.patterns.behavioral.visitor.model.Shape;
import victor.training.patterns.behavioral.visitor.model.Square;

import java.util.Arrays;
import java.util.List;

public class VisitorPlay {

	public static void main(String[] args) {
		List<Shape> shapes = Arrays.asList(
				new Square(10), 
				new Circle(5), 
				new Square(5));

		double totalPerimeter = 0;
		for (Shape shape : shapes) {
			if (shape instanceof Circle c) {
				totalPerimeter += c.getRadius() * 2 * Math.PI;
			} else if (shape instanceof Square s) {
				totalPerimeter += s.getEdge() * 4;
			} else {
				throw new RuntimeException("NO " + shape.getClass());
			}
		}
		System.out.println("Total perimeter: " + totalPerimeter);


		System.out.println("Total area: " + 0); // TODO

	}

}
