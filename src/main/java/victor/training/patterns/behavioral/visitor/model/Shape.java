package victor.training.patterns.behavioral.visitor.model;

import victor.training.patterns.behavioral.visitor.ShapeVisitor;

/*sealed*/ public interface Shape {
	void accept(ShapeVisitor visitor); // piece 1
}
