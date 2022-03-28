package victor.training.patterns.behavioral.visitor;

import victor.training.patterns.behavioral.visitor.model.Circle;
import victor.training.patterns.behavioral.visitor.model.EqTriangle;
import victor.training.patterns.behavioral.visitor.model.ShapeVisitor;
import victor.training.patterns.behavioral.visitor.model.Square;

public class AreaCalculatorVisitor implements ShapeVisitor {
   double totalArea = 0;

   @Override
   public void visit(Square square) {
      totalArea += square.getEdge() * square.getEdge();
   }

   @Override
   public void visit(Circle circle) {
      totalArea += Math.PI * circle.getRadius() * circle.getRadius();
   }

   @Override
   public void visit(EqTriangle eqTriangle) {
      totalArea += 0;// TODO
   }

   public double getTotalArea() {
      return totalArea;
   }
}
