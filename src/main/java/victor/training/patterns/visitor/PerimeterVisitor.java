package victor.training.patterns.visitor;

import victor.training.patterns.visitor.model.Circle;
import victor.training.patterns.visitor.model.Square;

public class PerimeterVisitor implements ShapeVisitor {

	private double totalPerimeter;
	
	@Override
	public void visit(Square square) {
		totalPerimeter += 4 * square.edge();
	}

	@Override
	public void visit(Circle circle) {
		totalPerimeter += 2 * Math.PI * circle.radius();
	}
	
	public double getTotalPerimeter() {
		return totalPerimeter;
	}

}
