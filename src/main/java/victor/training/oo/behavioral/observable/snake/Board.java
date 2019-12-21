package victor.training.oo.behavioral.observable.snake;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.awt.*;

class Board {
    private final Canvas canvas;
    private final int edgeSize;
    private final int pixelSize;

    public Board(Canvas canvas, int edgeSize) {
        this.canvas = canvas;
        this.edgeSize = edgeSize;
        pixelSize = (int) (canvas.getWidth() / edgeSize);
    }

    public int getEdgeSize() {
        return edgeSize;
    }

    public void drawSnakePoint(Point point) {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.LIGHTGRAY);
        g.fillRect(point.getX() * pixelSize, point.getY() * pixelSize, pixelSize, pixelSize);
        canvas.getGraphicsContext2D()
                .strokeRect(point.getX() * pixelSize + 1, point.getY() * pixelSize + 1, pixelSize - 2, pixelSize - 2);
    }
    public void drawFlower(Point point) {
        GraphicsContext g = canvas.getGraphicsContext2D();
        g.setFill(Color.ORANGE);
        g.fillRect(point.getX() * pixelSize, point.getY() * pixelSize, pixelSize, pixelSize);
//        canvas.getGraphicsContext2D()
//                .strokeRect(point.getX() * pixelSize + 1, point.getY() * pixelSize + 1, pixelSize - 2, pixelSize - 2);
    }

    public void clearPoint(Point point) {
        canvas.getGraphicsContext2D()
                .clearRect(point.getX() * pixelSize, point.getY() * pixelSize, pixelSize, pixelSize);
    }

    public boolean containsPoint(Point point) {
        return point.x >= 0 && point.x < getEdgeSize() && point.y >= 0 && point.y < getEdgeSize();
    }
}
