package victor.training.patterns.visitor;

import victor.training.patterns.visitor.model.Circle;
import victor.training.patterns.visitor.model.Rectangle;
import victor.training.patterns.visitor.model.Square;

import java.util.ArrayList;
import java.util.List;

public class AsTextVisitor implements ShapeVisitor{
    List<String> texts = new ArrayList<>();
    @Override
    public void visit(Square square) {
        texts.add("Patrat cu latura=" + square.getEdge());
    }

    @Override
    public void visit(Circle circle) {
    }

    @Override
    public void visit(Rectangle rectangle) {

        texts.add("Rectangle cu laturi=" +rectangle);
    }

    public List<String> getTexts() {
        return texts;
    }
}
