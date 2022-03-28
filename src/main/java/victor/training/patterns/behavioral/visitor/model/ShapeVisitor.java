package victor.training.patterns.behavioral.visitor.model;

public interface ShapeVisitor {
	void visit(Square square);

	void visit(Circle circle);

	void visit(EqTriangle eqTriangle);
}
