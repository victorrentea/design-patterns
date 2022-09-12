package victor.training.patterns.visitor;

import com.fasterxml.jackson.databind.node.ArrayNode;
import victor.training.patterns.visitor.model.Circle;
import victor.training.patterns.visitor.model.Rectangle;
import victor.training.patterns.visitor.model.Square;

import java.util.ArrayList;
import java.util.List;

public class WrapVIsitor implements ShapeVisitor {

    private List<ShapeWrapper> wrappers = new ArrayList<>();


    @Override
    public void visit(Square square) {
        wrappers.add(new SquareWrapper(square));
    }

    @Override
    public void visit(Circle circle) {
        wrappers.add(new CircleWrapper(circle));
    }

    @Override
    public void visit(Rectangle rectangle) {
        wrappers.add(new RectangleWrapper(rectangle));
    }
}
