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
        System.out.println("Total perimeter: " + sealed(shapes));

        double totalArea = 0;// TODO
        System.out.println("Total area: " + totalArea);
    }

    private static double oop(List<Shape> shapes) {
        double total = 0;
        for (Shape shape : shapes) {
            // TODO calculate perimeter depending on the type of the shape (Circle, Square,...)
            // 2 PI R.  4 E
//            total += shape.perimeter();

            if (shape instanceof Square square) {
                total += 4 * square.edge();
            } else if (shape instanceof Circle circle) {
                total += 2 * Math.PI * circle.radius();
            } else {
                // Acoperim RISK: mai adaugam noi tipuri de Shape
                // tot throws la runtime
                throw new IllegalArgumentException();
            }
        }
        return total;
    }

    // constrangeri: nu vrei/nu poti sa pui logica IN clasa Square/Circle
    // - e din jar
    // - e plin (e mare deja Square eg 600 linii)
    // - logica de pus in fiecare ARE MULTE IN COMUN eg. draw with ascii art
    ///    vreau cohesion
    // n-ai voie OOP: nu pui logica in clasele cu date

    //<editor-fold desc="Visitor Pattern">
    private static double visitor(List<Shape> shapes) {
        PerimeterVisitor perimeterCalculator = new PerimeterVisitor();
//        for (Shape shape : shapes) {
//            shape.accept(perimeterCalculator);
//        }
        return perimeterCalculator.getTotalPerimeter();
    }
    //</editor-fold>

    //<editor-fold desc="java 19 + sealed Shape interface">
    private static double sealed(List<Shape> shapes) {
//        return shapes.stream()
//                .mapToDouble(shape -> switch (shape) {
//                    case Square s -> s.edge() * 4;
//                    case Circle c -> c.radius() * Math.PI * 2;
//                    // nu e default, caci Shape e sealed enumerand
//                    // toate subtipurile posibile
//                })
//                .sum();
        return 0;
    }
    //</editor-fold>
}



