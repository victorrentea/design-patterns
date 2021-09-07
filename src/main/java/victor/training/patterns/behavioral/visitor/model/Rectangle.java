package victor.training.patterns.behavioral.visitor.model;

import lombok.Data;
import victor.training.patterns.behavioral.visitor.ShapeVisitor;

@Data
public class Rectangle implements Shape {
   private final int width, height;

   @Override
   public void accept(ShapeVisitor visitor) {
      visitor.visit(this);
   }
}
