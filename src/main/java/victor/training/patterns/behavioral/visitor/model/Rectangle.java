package victor.training.patterns.behavioral.visitor.model;

import victor.training.patterns.behavioral.visitor.ShapeVisitor;

public class Rectangle implements Shape {
   private final int w;
   private final int h;

   public Rectangle(int w, int h) {
      this.w = w;
      this.h = h;
   }

   @Override
   public void accept(ShapeVisitor visitor) {
      visitor.visit(this);
   }

   public int getH() {
      return h;
   }

   public int getW() {
      return w;
   }
}
