package victor.training.patterns.visitor.model;

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
		return 4 * edge;
	}

	public int getEdge() {
		return edge;
	}

}
