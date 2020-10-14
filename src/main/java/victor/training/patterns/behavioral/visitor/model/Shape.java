package victor.training.patterns.behavioral.visitor.model;

import victor.training.patterns.behavioral.visitor.ShapeVisitor;

public interface Shape {
	void accept(ShapeVisitor visitor);

	double getArea();
}
