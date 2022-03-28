package victor.training.patterns.behavioral.visitor.model;

public class EqTriangle implements Shape {
   private final double edge;

   public EqTriangle(double edge) {
      this.edge = edge;
   }

   public double getEdge() {
      return edge;
   }

   @Override
   public void accept(ShapeVisitor visitor) {
      visitor.visit(this);
   }
}
