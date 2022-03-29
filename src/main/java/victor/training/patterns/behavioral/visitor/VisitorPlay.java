package victor.training.patterns.behavioral.visitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import victor.training.patterns.behavioral.visitor.model.Circle;
import victor.training.patterns.behavioral.visitor.model.Shape;
import victor.training.patterns.behavioral.visitor.model.Square;

import java.util.Arrays;
import java.util.List;

public class VisitorPlay {
	private static final Logger log = LoggerFactory.getLogger(VisitorPlay.class); // MULTI-TON

	public static void main(String[] args) {
		List<Shape> shapes = Arrays.asList(
			new Square(10),
			new Circle(5),
			new Square(5));

		PerimeterCalculatorVisitor perimeterVisitor = new PerimeterCalculatorVisitor();

		for (Shape shape : shapes) {
			shape.accept(perimeterVisitor);
		}
		System.out.println("Total perimeter: " + perimeterVisitor.getTotal());

		AreaCalculatorVisitor areaVisitor = new AreaCalculatorVisitor();
		for (Shape shape : shapes) {
			shape.accept(areaVisitor);
		}
		System.out.println("Total area: " + areaVisitor.getTotalArea());
	}
}
