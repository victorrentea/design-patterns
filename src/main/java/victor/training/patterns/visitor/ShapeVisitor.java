package victor.training.patterns.visitor;

import victor.training.patterns.visitor.model.Circle;
import victor.training.patterns.visitor.model.Rectrangle;
import victor.training.patterns.visitor.model.Square;

public interface ShapeVisitor {
	void visit(Square square);

	void visit(Circle circle);

	void visit(Rectrangle rectrangle);
}
