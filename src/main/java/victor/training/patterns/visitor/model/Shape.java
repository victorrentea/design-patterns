package victor.training.patterns.visitor.model;

import victor.training.patterns.visitor.ShapeVisitor;

public sealed interface Shape permits Circle, Rectrangle, Square{

//	double getPerimeter();
	void accept(ShapeVisitor visitor);
}
