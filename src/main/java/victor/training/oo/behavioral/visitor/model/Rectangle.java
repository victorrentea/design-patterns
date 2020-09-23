package victor.training.oo.behavioral.visitor.model;

import victor.training.oo.behavioral.visitor.ShapeVisitor;

public class Rectangle implements Shape {

	private final int width,height;

	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visit(this);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
