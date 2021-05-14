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


		PerimeterCalculatorVisitor visitor = new PerimeterCalculatorVisitor();
		for (Shape shape : shapes) {
			shape.accept(visitor);
		}
		System.out.println("Total perimeter: " + visitor.getTotal());


		AreaCalculatorVisitor avisitor = new AreaCalculatorVisitor();
		for (Shape shape : shapes) {
			shape.accept(avisitor);
		}

		System.out.println("Total area: " + avisitor.getTotalArea()); // TODO

	}

}
