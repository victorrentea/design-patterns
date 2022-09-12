package victor.training.patterns.visitor.model;

import victor.training.patterns.visitor.ShapeVisitor;

public class Square implements Shape {

	private final int edge;

	public Square(int edge) {
		this.edge = edge;
	}

//	@Override
//	public String getText() {
//		return "Patrat " + edge;
//	}

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visit(this);
	}

	public int getEdge() {
		return edge;
	}

}
