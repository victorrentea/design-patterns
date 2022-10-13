package victor.training.patterns.visitor.model;

import victor.training.patterns.visitor.ShapeVisitor;

public interface Shape {
	void accept(ShapeVisitor visitor);

//	double computePerimeter();
}
