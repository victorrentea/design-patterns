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

		// MODEL INTANGIBIL: swaggergen, AST, modele de prin jar pe care lucrezi
		double perimeter = 0;
		PerimeterCalculatorVisitor visitor = new PerimeterCalculatorVisitor();
		for (Shape shape : shapes) {
			shape.accept(visitor);
		}

		System.out.println("Peri: " + visitor.getTotal());

//		PerimeterCalculatorVisitor perimeterCalculator = new PerimeterCalculatorVisitor();
//		for (Shape shape : shapes) {
//			shape.accept(perimeterCalculator);
//		}
//		System.out.println("Total perimeter: " + perimeterCalculator.getTotal());

		AreaCalculatorVisitor areaV = new AreaCalculatorVisitor();
		for (Shape shape : shapes) {
			shape.accept(areaV);
		}

		System.out.println("Total area: " + 0); // TODO

	}

}
