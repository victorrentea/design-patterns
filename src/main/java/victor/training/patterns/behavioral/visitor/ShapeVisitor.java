package victor.training.patterns.behavioral.visitor;

import victor.training.patterns.behavioral.visitor.model.Circle;
import victor.training.patterns.behavioral.visitor.model.Ellipsis;
import victor.training.patterns.behavioral.visitor.model.Square;

public interface ShapeVisitor {
   // overloading
   void visit(Square square);

   void visit(Circle circle);

   void visit(Ellipsis ellipsis);

}
