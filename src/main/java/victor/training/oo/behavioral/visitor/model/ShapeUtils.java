package victor.training.oo.behavioral.visitor.model;

public class ShapeUtils {
   public static double circleArea(Circle circle) {
      return Math.PI * circle.getRadius() * circle.getRadius();
   }
   public static double squareArea(Square square) {
      return square.getEdge() * square.getEdge();
   }
}
