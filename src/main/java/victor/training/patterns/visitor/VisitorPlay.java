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
                new Circle(5),
                new Square(5));

        System.out.println("Total perimeter: " + oop(shapes));
        System.out.println("Total perimeter: " + visitor(shapes));
//        System.out.println("Total perimeter: " + sealed(shapes));

        double totalArea = 0;// TODO
        System.out.println("Total area: " + totalArea);
    }

    private static double oop(List<Shape> shapes) {
        double total = 0;
        for (Shape shape : shapes) {
            // TODO compute perimeter depending on the type of the shape (Circle, Square,...)
        }
        return total;
    }


    //<editor-fold desc="Visitor Pattern">
    private static double visitor(List<Shape> shapes) {
        PerimeterVisitor perimeterCalculator = new PerimeterVisitor();
        for (Shape shape : shapes) {
            shape.accept(perimeterCalculator);
        }
        return perimeterCalculator.getTotalPerimeter();
    }
    //</editor-fold>

    //<editor-fold desc="java 19 + sealed Shape interface">
//    private static double sealed(List<Shape> shapes) {
//        return shapes.stream()
//                .mapToDouble(shape -> switch (shape) {
//                    case Square s -> s.edge() * 4;
//                    case Circle c -> c.radius() * Math.PI * 2;
//                })
//                .sum();
//    }
    //</editor-fold>
}



