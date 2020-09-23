package victor.training.oo.behavioral.visitor;

import victor.training.oo.behavioral.visitor.model.Circle;
import victor.training.oo.behavioral.visitor.model.Rectangle;
import victor.training.oo.behavioral.visitor.model.Square;

public class AreaVisitor implements ShapeVisitor {
   double sum;

   @Override
   public void visit(Square square) {
      sum += square.getEdge() * square.getEdge();
   }

   @Override
   public void visit(Circle circle) {
      sum += circle.getRadius() * circle.getRadius() * Math.PI;

   }

   @Override
   public void visit(Rectangle rectangle) {
      sum += rectangle.getHeight() * rectangle.getWidth();
   }

   public double getSum() {
      return sum;
   }
}
