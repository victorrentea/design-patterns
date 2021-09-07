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

		PerimeterCalculatorVisitor perimeterCalculator = new PerimeterCalculatorVisitor();
		double totalPerimeter = 0;
		for (Shape shape : shapes) {
//			if (shape instanceof Square) {
//				Square s = (Square) shape;
//				totalPerimeter += s.getEdge()*4;
//			} else if (shape instanceof Circle) {
//				Circle c = (Circle) shape;
//				totalPerimeter += c.getRadius() * 2 * Math.PI;
//			}

//			totalPerimeter += shape.getPerimeter();

			shape.accept(perimeterCalculator);
		}
		System.out.println("Total perimeter: " + totalPerimeter);
		System.out.println("Total perimeter: " + perimeterCalculator.getTotal());


		System.out.println("Total area: " + 0); // TODO

	}

}
