package victor.training.patterns.visitor.model;

import victor.training.patterns.visitor.ShapeVisitor;

sealed public interface Shape permits Circle, Square {
//    double area(); // do THIS whenever possible

    void accept(ShapeVisitor visitor);
}
