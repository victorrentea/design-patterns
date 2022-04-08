package victor.training.patterns.visitor.model;

public interface Shape {
	void accept(ShapeVisitor visitor);

	double getPerimeter();
}
