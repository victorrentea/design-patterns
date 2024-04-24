package victor.training.patterns.visitor.model;

import victor.training.patterns.visitor.ShapeVisitor;

public record Square(int edge) implements Shape {

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visit(this);
	}

}
