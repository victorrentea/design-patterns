package victor.training.patterns.visitor.model;

import victor.training.patterns.visitor.ShapeVisitor;

public record Circle(int radius) implements Shape {

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public double getPerimeter() {
		return radius * 2 * Math.PI;
	}

}
