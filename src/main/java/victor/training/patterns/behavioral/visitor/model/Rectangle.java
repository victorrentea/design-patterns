package victor.training.patterns.behavioral.visitor.model;

import victor.training.patterns.behavioral.visitor.ShapeVisitor;

public class Rectangle implements Shape {
	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visit(this);
	}
}
