package victor.training.patterns.behavioral.visitor;

import victor.training.patterns.behavioral.visitor.model.Circle;
import victor.training.patterns.behavioral.visitor.model.Rectangle;
import victor.training.patterns.behavioral.visitor.model.Square;

public interface ShapeVisitor {
	void visit(Square square);

	void visit(Circle circle);

	void visit(Rectangle rectangle);
}
