package victor.training.patterns.visitor.model;

import victor.training.patterns.visitor.ShapeVisitor;

sealed public interface Shape
        permits Circle, Square, Rectangle {
    void accept(ShapeVisitor visitor);

    double getPerimeter();
}
