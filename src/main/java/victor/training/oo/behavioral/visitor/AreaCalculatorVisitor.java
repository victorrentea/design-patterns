package victor.training.oo.behavioral.visitor;

import victor.training.oo.behavioral.visitor.model.Circle;
import victor.training.oo.behavioral.visitor.model.Square;

public class AreaCalculatorVisitor implements ShapeVisitor {
   private double totalArea;
   @Override
   public void visit(Square square) {
      totalArea += square.getEdge() * square.getEdge();
   }

   @Override
   public void visit(Circle circle) {
      totalArea += circle.getRadius() * circle.getRadius() * Math.PI;
   }

   public double getTotalArea() {
      return totalArea;
   }
}
