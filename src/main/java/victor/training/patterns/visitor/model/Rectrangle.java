package victor.training.patterns.visitor.model;

import lombok.Value;
import victor.training.patterns.visitor.ShapeVisitor;

@Value
public class Rectrangle implements  Shape {
    int w,h;

    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}
