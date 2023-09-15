package victor.training.patterns.visitor.model;

import victor.training.patterns.visitor.ShapeVisitor;

sealed // java 21
public interface Shape
    permits Circle, Square // java 21
  // nici un alt subtip nu poate compila
{
    void accept(ShapeVisitor visitor);

  double perimeter();
}
