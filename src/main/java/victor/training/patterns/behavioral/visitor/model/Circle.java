package victor.training.patterns.behavioral.visitor.model;

import victor.training.patterns.behavioral.visitor.ShapeVisitor;

public class Circle implements Shape {

	private final int radius;

	public Circle(int radius) {
		this.radius = radius;
	}

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visit(this);
	}

//	@Override
//	public double getArea() {
//		return Math.PI * radius * radius;
//	}

	public int getRadius() {
		return radius;
	}
}
