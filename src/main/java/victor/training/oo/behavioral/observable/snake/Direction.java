package victor.training.oo.behavioral.observable.snake;

import javafx.scene.input.KeyCode;

import java.awt.*;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public enum Direction {
    DOWN(KeyCode.DOWN,0, 1),
    UP(KeyCode.UP,0, -1),
    LEFT(KeyCode.LEFT,-1, 0),
    RIGHT(KeyCode.RIGHT,1, 0);
    public final KeyCode code;
    public final int x;
    public final int y;

    Direction(KeyCode code, int x, int y) {
        this.code = code;
        this.x = x;
        this.y = y;
    }

    public static Optional<Direction> fromKeyCode(KeyCode code) {
        for (Direction direction : values()) {
            if (direction.code == code) {
                return of(direction);
            }
        }
        return empty();
    }

    public Direction getOpposite() {
        return Stream.of(values()).filter(other -> other.x == -this.x && other.y == -this.y).findFirst().get();
    }

    public Point move(Point point) {
        return new Point(point.x + x, point.y + y);
    }
}
