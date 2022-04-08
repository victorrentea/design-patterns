package victor.training.patterns.visitor;

import victor.training.patterns.visitor.model.Circle;
import victor.training.patterns.visitor.model.ShapeVisitor;
import victor.training.patterns.visitor.model.Square;

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
	
	public double getTotal() {
		return total;
	}

}
