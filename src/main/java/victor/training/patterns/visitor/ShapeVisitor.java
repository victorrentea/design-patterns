package victor.training.patterns.visitor;

import victor.training.patterns.visitor.model.Circle;
import victor.training.patterns.visitor.model.Rectangle;
import victor.training.patterns.visitor.model.Square;

public interface ShapeVisitor {
	void visitSquare(Square square);

	void visitCircle(Circle circle);

	void visitRectangle(Rectangle rectangle);
}
