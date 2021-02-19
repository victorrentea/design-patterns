package victor.training.patterns.behavioral.visitor;

import victor.training.patterns.behavioral.visitor.model.Circle;
import victor.training.patterns.behavioral.visitor.model.Rectangle;
import victor.training.patterns.behavioral.visitor.model.Square;

public class PerimeterCalculatorVisitor implements ShapeVisitor {

	private double total;

	@Override
	public void visit(Square square) {
		f();
		total += 4 * square.getEdge();
	}

	private void f() {
	}

	@Override
	public void visit(Circle circle) {
		f();
		total += circle.getRadius() * 2 * Math.PI;
	}

	@Override
	public void visit(Rectangle rectangle) {
		f();
		total += 2 * rectangle.getW() + 2 * rectangle.getH();
	}

	public double getTotal() {
		return total;
	}

}
