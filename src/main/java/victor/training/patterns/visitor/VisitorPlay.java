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


		double totalPerimeter = shapes.stream().mapToDouble(Shape::getPerimeter).sum();

		AreaVisitor visitor = new AreaVisitor();

		for (Shape shape : shapes) {
			shape.accept(visitor);
		}
		System.out.println(visitor.getTotalArea());

//		PerimeterCalculatorVisitor perimeterCalculator = new PerimeterCalculatorVisitor();
//		for (Shape shape : shapes) {
//			shape.accept(perimeterCalculator);
//		}
//		System.out.println("Total perimeter: " + perimeterCalculator.getTotal());
//
//
//		System.out.println("Total area: " + 0); // TODO

	}

}