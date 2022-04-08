package victor.training.patterns.visitor;

import victor.training.patterns.visitor.model.Circle;
import victor.training.patterns.visitor.model.ShapeVisitor;
import victor.training.patterns.visitor.model.Square;

public class AreaVisitor implements ShapeVisitor {
   double totalArea;

   @Override
   public void visit(Square square) {
      totalArea += square.getEdge() * square.getEdge();
   }

   @Override
   public void visit(Circle circle) {
      totalArea = circle.getRadius() * circle.getRadius() * Math.PI;
   }

   public double getTotalArea() {
      return totalArea;
   }
}
