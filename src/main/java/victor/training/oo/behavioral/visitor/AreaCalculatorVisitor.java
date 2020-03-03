package victor.training.oo.behavioral.visitor;

import victor.training.oo.behavioral.visitor.model.Circle;
import victor.training.oo.behavioral.visitor.model.Square;

public class AreaCalculatorVisitor implements ShapeVisitor {
    private double total;

    @Override
    public void visit(Square square) {
        total += square.getEdge() * square.getEdge();
    }

    @Override
    public void visit(Circle circle) {
        total += circle.getRadius() * circle.getRadius() * Math.PI;
    }

    public double getTotal() {
        return total;
    }
}
