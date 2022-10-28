package victor.training.patterns.visitor.model;

import victor.training.patterns.visitor.ShapeVisitor;

public record Circle(int radius) implements Shape {

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visitCircle(this);
	}

	@Override
	public double getPerimeter() {
		String s = "<shit>breakign MVC</shit>";
		// xml inside your core model - anarchitect comes and says... "neah" WRONG NO
		return radius * 2 * Math.PI;
	}

}
