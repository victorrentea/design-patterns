package victor.training.patterns.behavioral.visitor;

import victor.training.patterns.behavioral.visitor.model.Circle;
import victor.training.patterns.behavioral.visitor.model.Ellipsis;
import victor.training.patterns.behavioral.visitor.model.Square;

public class PerimeterCalculatorVisitor implements ShapeVisitor {

	private double total;

	@Override
	public void visit(Square square) {
		total += 4 * square.getEdge();
	}

	@Override
	public void visit(Circle circle) {
		total += circle.getRadius() * 2 * Math.PI;
	}

	@Override
	public void visit(Ellipsis ellipsis) {

	}

	public double getTotal() {
		return total;
	}

}
