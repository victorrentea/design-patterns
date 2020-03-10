package victor.training.oo.behavioral.visitor;

import victor.training.oo.behavioral.visitor.model.Circle;
import victor.training.oo.behavioral.visitor.model.Square;

public class AreaCalculatorVisitor implements ShapeVisitor {
    private double area;
    @Override
    public void visit(Square square) {
        area += square.getEdge() * square.getEdge();
    }

    @Override
    public void visit(Circle circle) {
        area += Math.PI * circle.getRadius() * circle.getRadius();
    }

    public double getArea() {
        return area;
    }
}
