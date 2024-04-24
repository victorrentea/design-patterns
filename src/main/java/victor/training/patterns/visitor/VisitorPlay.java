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

        double totalArea = 0;// TODO
        System.out.println("Total area: " + totalArea);
    }

}



