package victor.training.patterns.visitor;

import victor.training.patterns.visitor.model.Rectangle;

public class RectangleWrapper extends ShapeWrapper implements Textualizable{
    private final Rectangle rectangle;

    public RectangleWrapper(Rectangle rectangle) {
        super();
        this.rectangle = rectangle;
    }

    @Override
    public String asText() {
        return "Drept";
    }
}
