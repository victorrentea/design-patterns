package victor.training.patterns.behavioral.visitor;

import victor.training.patterns.behavioral.visitor.model.Circle;
import victor.training.patterns.behavioral.visitor.model.Rectangle;
import victor.training.patterns.behavioral.visitor.model.Square;

public class AsciiArtDraw implements ShapeVisitor
{
   @Override
   public void visit(Square square) {

   }

   @Override
   public void visit(Circle circle) {

   }

   @Override
   public void visit(Rectangle rectangle) {

   }
   //common code for padding, spaces, geometry common to drawing both a square and a circle.
}
