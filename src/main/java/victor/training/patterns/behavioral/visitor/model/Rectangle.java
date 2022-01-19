package victor.training.patterns.behavioral.visitor.model;

import victor.training.patterns.behavioral.visitor.ShapeVisitor;

public class Rectangle implements Shape {
   private final int height, width;

   public Rectangle(int height, int width) {
      this.height = height;
      this.width = width;
   }

   public int getHeight() {
      return height;
   }

   public int getWidth() {
      return width;
   }

   @Override
   public void accept(ShapeVisitor visitor) {
      visitor.visit(this);
   }
}
