package victor.training.patterns.visitor;

import victor.training.patterns.visitor.model.Circle;
import victor.training.patterns.visitor.model.Rectangle;
import victor.training.patterns.visitor.model.Square;

public class AreaCalculatorVisitor implements ShapeVisitor {

	private double total;
	
	@Override
	public void visit(Square square) {
		total += square.getEdge() * square.getEdge();
	}

	@Override
	public void visit(Circle circle) {
		total += circle.getRadius() * circle.getRadius() * Math.PI;
	}

	@Override
	public void visit(Rectangle rectangle) {
		//
	}

	public double getTotal() {
		return total;
	}

}
