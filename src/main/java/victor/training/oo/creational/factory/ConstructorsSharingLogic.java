package victor.training.oo.creational.factory;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ConstructorsSharingLogic {
   public static void main(String[] args) {
      System.out.println(new ConstructorsSharingLogic().getShapes("HAPPY"));
   }

   List<Shape> getShapes(String mood) {
      return Arrays.asList(new Square(mood), new Circle(mood));
   }
}


abstract class Shape {
   protected Color color;
}

class Square extends Shape {
   private final int edge;

   public Square(String mood) {
      if ("HAPPY".equals(mood)) {
         color = Color.YELLOW;
      } else {
         color = Color.WHITE;
      }
      edge = 10 + new Random().nextInt(10);
   }

   public int getEdge() {
      return edge;
   }
}

class Circle extends Shape {
   private final int radius;

   public Circle(String mood) {
      if ("HAPPY".equals(mood)) {
         color = Color.YELLOW;
      } else {
         color = Color.WHITE;
      }
      radius = 10 + new Random().nextInt(10);
   }

   public int getRadius() {
      return radius;
   }
}