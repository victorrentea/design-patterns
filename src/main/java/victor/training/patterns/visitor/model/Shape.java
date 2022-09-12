package victor.training.patterns.visitor.model;

import victor.training.patterns.visitor.ShapeVisitor;

public interface Shape {
//    String getText();

    void accept(ShapeVisitor visitor);
}
