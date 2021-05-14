package victor.training.patterns.behavioral.visitor;

import victor.training.patterns.behavioral.visitor.model.Circle;
import victor.training.patterns.behavioral.visitor.model.Square;

public class AreaCalculatorVisitor implements ShapeVisitor {
   double totalArea = 0;

   @Override
   public void visit(Square square) {
      totalArea += square.getEdge() * square.getEdge();
   }

   @Override
   public void visit(Circle circle) {
      totalArea += circle.getRadius() * circle.getRadius() * StrictMath.PI;
   }

   public double getTotalArea() {
      return totalArea;
   }
}
