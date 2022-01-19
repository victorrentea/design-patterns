package victor.training.patterns.behavioral.visitor;

import victor.training.patterns.behavioral.visitor.model.Circle;
import victor.training.patterns.behavioral.visitor.model.Rectangle;
import victor.training.patterns.behavioral.visitor.model.Square;

public class AreaVisitor implements ShapeVisitor {
   private double total;

   @Override
   public void visit(Square square) {
      total += square.getEdge() * square.getEdge();
   }

   @Override
   public void visit(Circle circle) {
      total += circle.getRadius() * circle.getRadius() * Math.PI;
   }

   @Override
   public void visit(Rectangle rectangle) {
      total += rectangle.getWidth() * rectangle.getHeight();
   }

   public double getTotal() {
      return total;
   }
}
