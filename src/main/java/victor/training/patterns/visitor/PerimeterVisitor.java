package victor.training.patterns.visitor;

import victor.training.patterns.visitor.model.Circle;
import victor.training.patterns.visitor.model.Rectangle;
import victor.training.patterns.visitor.model.Square;

public class PerimeterVisitor implements ShapeVisitor {

	private double totalPerimeter;
	
	@Override
	public void visitSquare(Square square) {
		totalPerimeter += 4 * square.edge();
	}

	@Override
	public void visitCircle(Circle circle) {
		totalPerimeter += 2 * Math.PI * circle.radius();
	}

	@Override
	public void visitRectangle(Rectangle rectangle) {
		totalPerimeter += 2 * (rectangle.w() + rectangle.h());
	}

	public double getTotalPerimeter() {
		return totalPerimeter;
	}

}
