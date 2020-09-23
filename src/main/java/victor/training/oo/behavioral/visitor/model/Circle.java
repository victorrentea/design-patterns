package victor.training.oo.behavioral.visitor.model;

import victor.training.oo.behavioral.visitor.ShapeVisitor;

public class Circle implements Shape {

	private final int radius;

	public Circle(int radius) {
		this.radius = radius;
	}

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public double getPerimeter() {
		return
			getRadius() * 2 *Math.PI;
	}

	public int getRadius() {
		return radius;
	}
}
