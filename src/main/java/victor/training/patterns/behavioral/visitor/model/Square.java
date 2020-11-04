package victor.training.patterns.behavioral.visitor.model;

import victor.training.patterns.behavioral.visitor.ShapeVisitor;

public class Square implements Shape {

	private final int edge;

	public Square(int edge) {
		this.edge = edge;
	}

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visit(this);
	}

	public int getEdge() {
		return edge;
	}

}
