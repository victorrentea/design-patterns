package victor.training.patterns.behavioral.visitor;

import victor.training.patterns.behavioral.visitor.model.Circle;
import victor.training.patterns.behavioral.visitor.model.Rectangle;
import victor.training.patterns.behavioral.visitor.model.Square;

public class AreaVisitor implements ShapeVisitor{
   double totalArea;
   @Override
   public void visit(Square square) {
      totalArea += square.getEdge() * square.getEdge();
   }

   @Override
   public void visit(Circle circle) {
      totalArea += Math.PI * circle.getRadius() * circle.getRadius();

   }

   @Override
   public void visit(Rectangle rectangle) {

   }

   public double getTotalArea() {
      return totalArea;
   }
}
