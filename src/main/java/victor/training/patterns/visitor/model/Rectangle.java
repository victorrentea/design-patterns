package victor.training.patterns.visitor.model;

import victor.training.patterns.visitor.ShapeVisitor;

public record Rectangle(int w, int h) implements Shape{
    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visitRectangle(this);
    }

    @Override
    public double getPerimeter() {
        return 0;
    }
}
