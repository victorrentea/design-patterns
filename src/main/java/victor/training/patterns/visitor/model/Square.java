package victor.training.patterns.visitor.model;

import victor.training.patterns.visitor.ShapeVisitor;

public class Square implements Shape {

	private final int edge;

	public Square(int edge) {
		this.edge = edge;
	}

	// THIS SINGLE METHOD. no extra logic inside.
	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visit(this);
	}

	public int getEdge() {
		return edge;
	}

}
