package victor.training.patterns.visitor;

import victor.training.patterns.visitor.model.Circle;
import victor.training.patterns.visitor.model.Square;

public class AreaVisitor implements ShapeVisitor{
  private double totalArea = 0;
  @Override
  public void visit(Square square) {
    totalArea += square.edge() * square.edge();
  }

  @Override
  public void visit(Circle circle) {
     totalArea += Math.PI * circle.radius() * circle.radius();
  }

  public double getTotalArea() {
    return totalArea;
  }
}
