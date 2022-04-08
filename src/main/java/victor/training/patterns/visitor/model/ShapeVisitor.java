package victor.training.patterns.visitor.model;

import victor.training.patterns.visitor.model.Circle;
import victor.training.patterns.visitor.model.Square;

public interface ShapeVisitor {
	void visit(Square square);

	void visit(Circle circle);
}
