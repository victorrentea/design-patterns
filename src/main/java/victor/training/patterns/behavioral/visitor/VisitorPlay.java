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
//			totalPerimeter += shape.perimeter(); // this would make you Java / OOP teacher proud!
			shape.accept(perimeterCalculator);
		}
		System.out.println("Total perimeter: " + perimeterCalculator.getTotal());


		AreaCalculatorVisitor areaVisitor = new AreaCalculatorVisitor();
		for (Shape shape : shapes) {
			shape.accept(areaVisitor);
		}
		System.out.println("Total perimeter: " + areaVisitor.getTotal());


		System.out.println("Total area: " + 0); // TODO

	}

}
