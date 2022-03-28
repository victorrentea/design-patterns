package victor.training.patterns.behavioral.visitor.model;

public class Circle implements Shape {

	private final int radius;

	public Circle(int radius) {
		this.radius = radius;
	}

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visit(this);
	}

	public int getRadius() {
		return radius;
	}
}
