package victor.training.oo.behavioral.visitor.model;

import victor.training.oo.behavioral.visitor.ShapeVisitor;

public class Square implements Shape {

	private final int edge;

	public Square(int edge) {
		this.edge = edge;
	}

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public double getPerimeter() {
		return edge * 4;
	}

	public int getEdge() {
		return edge;
	}

}
