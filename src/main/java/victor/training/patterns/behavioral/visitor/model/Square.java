package victor.training.patterns.behavioral.visitor.model;

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
