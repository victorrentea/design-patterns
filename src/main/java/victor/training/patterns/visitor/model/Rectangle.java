package victor.training.patterns.visitor.model;

import lombok.Getter;
import victor.training.patterns.visitor.ShapeVisitor;
@Getter
public class Rectangle implements Shape{
    private final int l,h;

    public Rectangle(int l, int h) {
        this.l = l;
        this.h = h;
    }


    @Override
    public void accept(ShapeVisitor visitor) {
        visitor.visit(this);
    }
}
