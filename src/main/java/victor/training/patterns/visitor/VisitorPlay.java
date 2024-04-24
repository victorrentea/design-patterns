package victor.training.patterns.visitor;

import victor.training.patterns.visitor.model.Circle;
import victor.training.patterns.visitor.model.Shape;
import victor.training.patterns.visitor.model.Square;

import java.util.Arrays;
import java.util.List;

public class VisitorPlay {

    public static void main(String[] args) {
        List<Shape> shapes = List.of(
                new Square(10),
                new Circle(5), // = 2 * 3.14 * 5 = PI * r^2
                new Square(5));


        double totalArea = 0;
        // #1 OOP: add an area() method to Shape interface and the subclasses to calculate the area
        // the logic could be UGLY, LARGE or the data structures could be NOT MODIFIABLE (generated/in jar)
        // !! wrap behavior around the data class CircleWrapper {Circle circle}

        // #2 instanceof
//        for (Shape shape : shapes) {
//            if (shape instanceof Square square) { // 'instanceof' is not OOP . so what?!
//                totalArea += square.edge() * square.edge();
//            } else if (shape instanceof Circle circle) {
//                totalArea += Math.PI * circle.radius() * circle.radius();
//            } else {
//                throw new IllegalArgumentException("JDD: Unknown shape: " + shape); // RISK
//            }
//        }

        // #3 Visitor pattern - the ugliest design pattern in the GoF set.
//        AreaVisitor areaVisitor = new AreaVisitor();
//        for (Shape shape : shapes) {
//            shape.accept(areaVisitor);
//        }
//        totalArea= areaVisitor.getTotalArea();

// let {a,b} = f();
        for (Shape shape : shapes) {
            totalArea += switch (shape) { // java 21
                case Circle(int radius) -> Math.PI * radius * radius;
                case Square(int edge) -> edge * edge;
            }; // scala, kotlin, clojure....
        }


        System.out.println("Total area: " + totalArea);
    }

}



