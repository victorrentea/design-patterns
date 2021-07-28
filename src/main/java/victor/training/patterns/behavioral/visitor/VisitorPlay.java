package victor.training.patterns.behavioral.visitor;

import victor.training.patterns.behavioral.visitor.model.Circle;
import victor.training.patterns.behavioral.visitor.model.Rectangle;
import victor.training.patterns.behavioral.visitor.model.Shape;
import victor.training.patterns.behavioral.visitor.model.Square;

import java.util.Arrays;
import java.util.List;

public class VisitorPlay {

	public static void main(String[] args) {
		List<Shape> shapes = Arrays.asList(
			new Square(10),
			new Circle(5),
			new Square(5),
			new Rectangle(2, 3));

		double totalPerimeter = 0;
		PerimeterCalculatorVisitor perimeter = new PerimeterCalculatorVisitor();
		for (Shape shape : shapes) {
			shape.accept(perimeter);
		}
		System.out.println("Total perimeter: " + perimeter.getTotal());


		AreaVisitor areaVisitor = new AreaVisitor();
		for (Shape shape : shapes) {
			shape.accept(areaVisitor);
		}

		System.out.println("Total area: " + areaVisitor.getTotal()); // TODO

	}

}
//interface X extends Shape {
//
//}
//class XImpl implements X {
//
//}
