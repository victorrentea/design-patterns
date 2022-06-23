package victor.training.patterns.visitor;

import victor.training.patterns.visitor.model.Circle;
import victor.training.patterns.visitor.model.Rectrangle;
import victor.training.patterns.visitor.model.Square;

public class AreaVisitor
implements  ShapeVisitor{
    @Override
    public void visit(Square square) {

    }

    @Override
    public void visit(Circle circle) {

    }

    @Override
    public void visit(Rectrangle rectrangle) {

    }
}
