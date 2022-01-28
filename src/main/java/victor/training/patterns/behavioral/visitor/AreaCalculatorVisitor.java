package victor.training.patterns.behavioral.visitor;

import victor.training.patterns.behavioral.visitor.model.Circle;
import victor.training.patterns.behavioral.visitor.model.Ellipsis;
import victor.training.patterns.behavioral.visitor.model.Square;

public class AreaCalculatorVisitor implements ShapeVisitor {
   double total;

   public double getTotal() {
      return total;
   }

   @Override
   public void visit(Square square) {
      total += square.getEdge() * square.getEdge();
   }

   @Override
   public void visit(Circle circle) {
      total += circle.getRadius() * circle.getRadius() * Math.PI;
   }

   @Override
   public void visit(Ellipsis ellipsis) {

   }
}
