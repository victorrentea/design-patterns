package victor.training.oo.creational.factory;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class CreereCuLogicaComuna {
    public static void main(String[] args) {
        System.out.println(new CreereCuLogicaComuna().getShapes("HAPPY"));
    }

    List<Shape> getShapes(String mood) {
        return Arrays.asList(new Square(mood), new Circle(mood));
    }
}


abstract class Shape {
    private final Color color;
    protected Shape(Color color) {
        this.color = color;
    }
}
class Square extends Shape{
    private final int edge;
    Square(String mood) {
        super("HAPPY".equals(mood) ? Color.YELLOW : Color.WHITE);
        edge = 10 + new Random().nextInt(10);
    }
}
class Circle extends Shape{
    private final int radius;
    Circle(String mood) {
        super("HAPPY".equals(mood) ? Color.YELLOW : Color.WHITE);
        radius = 10 + new Random().nextInt(10);
    }
}