package victor.training.patterns.visitor;

import victor.training.patterns.visitor.model.Circle;
import victor.training.patterns.visitor.model.Rectrangle;
import victor.training.patterns.visitor.model.Shape;
import victor.training.patterns.visitor.model.Square;

import java.util.Arrays;
import java.util.List;

public class VisitorPlay {

	public static void main(String[] args) {
		List<Shape> shapes = Arrays.asList(
			new Square(10),
			new Circle(5),
			new Square(5),
				new Rectrangle(2,3));

		double totalPerimeter = 0;
		PerimeterCalculatorVisitor perimeterCalculatorVisitor = new PerimeterCalculatorVisitor();
		for (Shape shape : shapes) {
			// DO THIS anytime you can : 99%
//			totalPerimeter += shape.getPerimeter();

			// risk: forget one subtype
//			if (shape instanceof Square s) {
//				totalPerimeter += 4 * s.getEdge();
//			} else if (shape instanceof Circle c) {
//				totalPerimeter += 2 * Math.PI * c.getRadius();
//			}

			shape.accept(perimeterCalculatorVisitor);

//			totalPerimeter += switch (shape) {
//				case Square(edge) ->  4 * s.getEdge();
//				case Rectangle(w,h) -> 2*(w+h);
//				case Circle(w,h) -> 2*(w+h);
//			}
		}
		System.out.println("Total perimeter: " + perimeterCalculatorVisitor.getTotal());


		System.out.println("Total area: " + 0); // TODO

	}

}
