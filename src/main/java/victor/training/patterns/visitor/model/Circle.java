package victor.training.patterns.visitor.model;

import victor.training.patterns.visitor.ShapeVisitor;

public class Circle implements Shape {

	private final int radius;

	public Circle(int radius) {
		this.radius = radius;
	}

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visit(this);
	}

	public double computePerimeter() {
		return 2 * Math.PI * radius;
	}

	public int getRadius() {
		return radius;
	}
}
