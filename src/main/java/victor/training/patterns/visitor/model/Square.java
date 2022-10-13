package victor.training.patterns.visitor.model;

import victor.training.patterns.visitor.ShapeVisitor;

public class Square implements Shape {

	private final int edge;

	public Square(int edge) {
		this.edge = edge;
	}

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visit(this);
	}

	public double computePerimeter() {
		return 4 * edge;
	}

	public int getEdge() {
		return edge;
	}

}
