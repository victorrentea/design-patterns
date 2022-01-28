package victor.training.patterns.behavioral.visitor.model;

import victor.training.patterns.behavioral.visitor.ShapeVisitor;

// if this appears tomorrow i get a runtime error in my VisitorPlay
public class Ellipsis implements Shape {
   private final double small, big;

   public Ellipsis(double small, double big) {
      this.small = small;
      this.big = big;
   }

   @Override
   public void accept(ShapeVisitor visitor) {
      visitor.visit(this);
   }
}
