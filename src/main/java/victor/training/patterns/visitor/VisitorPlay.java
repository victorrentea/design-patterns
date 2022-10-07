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


		int sum = 0;
		PerimeterCalculatorVisitor perimeterCalculator = new PerimeterCalculatorVisitor();
		for (Shape shape : shapes) {
//			sum += shape.getPerimeter(); // OOP

			shape.accept(perimeterCalculator);
//			if (shape instanceof Circle c) {
//				sum += c.getRadius() * 2 * Math.PI;
//			} else if (shape instanceof Square s) {
//				sum += 4 * s.getEdge();
//			} else {
//				throw new IllegalArgumentException("JDD should never happen");
//			}
			// java 21 sealed classes + destructuring
//			switch (shape) {
//				Circle(radius) ->
//				Rectagle(radius) ->
//				Square(radius) ->
//			}
		}
//Map<Class, Function>


		for (Shape shape : shapes) {
		}
		System.out.println("Total perimeter: " + perimeterCalculator.getTotal());


		System.out.println("Total area: " + 0); // TODO

	}

}
// you implement a single global method on your objects so that you can plug in different method.
