package victor.training.patterns.visitor.model;

import victor.training.patterns.visitor.ShapeVisitor;

public record Square(int edge) implements Shape {

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visitSquare(this);
	}

	@Override
	public double getPerimeter() {
		return edge * 4;
	}

}
