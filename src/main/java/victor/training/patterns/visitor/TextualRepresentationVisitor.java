package victor.training.patterns.visitor;

import victor.training.patterns.visitor.model.Circle;
import victor.training.patterns.visitor.model.Rectangle;
import victor.training.patterns.visitor.model.Square;

// in afara modelului, al mine in cod.
public class TextualRepresentationVisitor implements ShapeVisitor{
    private String text = "";
    @Override
    public void visit(Square square) {
        setText(getText() + "Patrat latura=" + square.getEdge()+ "\n");
    }

    @Override
    public void visit(Circle circle) {
        setText(getText() + "Cerc raza=" + circle.getRadius()+ "\n");

    }

    @Override
    public void visit(Rectangle rectangle) {

    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
