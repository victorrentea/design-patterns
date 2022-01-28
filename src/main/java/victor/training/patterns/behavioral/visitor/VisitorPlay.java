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

		PerimeterCalculatorVisitor perimeterVisitor = new PerimeterCalculatorVisitor();
		for (Shape shape : shapes) {
			shape.accept(perimeterVisitor);

//			if (shape instanceof Circle c) {
//				totalPerimeter += c.getRadius() * 2 * Math.PI;
//			} else if (shape instanceof Square s) {
//				totalPerimeter += s.getEdge() * 4;
//			} else {
//				throw new RuntimeException("NO " + shape.getClass());
//			}

//			totalPerimeter += switch (shape) {
//				case Circle(radius) -> radius * 2 + Math.PI;
//				case Square(radius) -> radius * 2 + Math.PI;
//				case Ellipsis(radius) -> radius * 2 + Math.PI;
//			}
//			if (shape instanceof Circle c) {
//				totalPerimeter += c.getRadius() * 2 * Math.PI;
//			} else if (shape instanceof Square s) {
//				totalPerimeter += s.getEdge() * 4;
//			} else {
//				throw new RuntimeException("NO " + shape.getClass());
//			}

		}
		System.out.println("Total perimeter: " + perimeterVisitor.getTotal());

		AreaCalculatorVisitor areaVisitor = new AreaCalculatorVisitor();
		for (Shape shape : shapes) {
			shape.accept(areaVisitor);
		}

		System.out.println("Total area: " + areaVisitor.getTotal()); // TODO

	}

}
//			if (shape instanceof Circle c) {
//				totalPerimeter += c.getRadius() * 2 * Math.PI;
//			} else if (shape instanceof Square s) {
//				totalPerimeter += s.getEdge() * 4;
//			} else {
//				throw new RuntimeException("NO " + shape.getClass());
//			}
