package victor.training.patterns.visitor.model;

import victor.training.patterns.visitor.ShapeVisitor;

public class Rectangle implements Shape {
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}
