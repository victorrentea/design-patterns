package victor.training.patterns.behavioral.visitor;

import victor.training.patterns.behavioral.visitor.model.Circle;
import victor.training.patterns.behavioral.visitor.model.Rectangle;
import victor.training.patterns.behavioral.visitor.model.Square;

public class AreaCalculatorVisitor implements ShapeVisitor {
   private double total = 0;

   @Override
   public void visit(final Square square) {
      total += square.getEdge() * square.getEdge();
   }

   @Override
   public void visit(final Circle circle) {
      total += circle.getRadius() * circle.getRadius() * Math.PI;
   }

   @Override
   public void visit(final Rectangle rectangle) {

   }

   public double getTotal() {
      return total;
   }
}
