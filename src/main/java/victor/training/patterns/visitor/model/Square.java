package victor.training.patterns.visitor.model;

import victor.training.patterns.visitor.ShapeVisitor;

public record Square(int edge) implements Shape {

	@Override
	public void accept(ShapeVisitor visitor) {
		visitor.visit(this);
		// backdoor. allowing us to sneak in any behavior we want
		// in a type-safe way, without modifying the Shape interface
	}

}
