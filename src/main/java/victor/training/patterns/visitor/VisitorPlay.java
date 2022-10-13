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

//		PerimeterCalculatorVisitor perimeterCalculator = new PerimeterCalculatorVisitor();
//		for (Shape shape : shapes) {
//			shape.accept(perimeterCalculator);
//		}
//		System.out.println("Total perimeter: " + perimeterCalculator.getTotal());


		double sum = 0;
		for (Shape shape : shapes) {
			sum += computePerimeter(shape);
		}
		System.out.println("Total perimeter: " + sum);


		System.out.println("Total area: " + 0); // TODO

	}

	private static double computePerimeter(Shape shape) {
		if (shape instanceof Square) {
			Square sq = (Square) shape;
			return sq.getEdge() * 4;
		} else if (shape instanceof Circle) {
			Circle c = (Circle) shape;
			return c.getRadius() * 2 * Math.PI;
		} else {
			throw new IllegalArgumentException("JDD sper sa nu se " +
											   "arunce vreodata");
		}
	}

}
