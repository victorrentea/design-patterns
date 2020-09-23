package victor.training.oo.behavioral.visitor;

import victor.training.oo.behavioral.visitor.model.Circle;
import victor.training.oo.behavioral.visitor.model.Rectangle;
import victor.training.oo.behavioral.visitor.model.Square;

public class AsciiDrawVisitor implements ShapeVisitor {
   double sum;

   @Override
   public void visit(Square square) {

      sum += square.getEdge() * square.getEdge();
   }
   public void m() {
      // HEAVY LOGIC S HARED WITH ALL OTHER DRAW (pt fiecare tip)

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
