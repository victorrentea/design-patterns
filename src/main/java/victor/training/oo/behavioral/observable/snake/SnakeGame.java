package victor.training.oo.behavioral.observable.snake;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subscriptions.Subscriptions;
import victor.training.oo.behavioral.observable.bug.FxPlatformScheduler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class SnakeGame extends Application {

    public static final int SCREEN_EDGE = 800;
    public static final int PIXEL_SIZE = 40;
    private Canvas sky;
    private PublishSubject<Boolean> gameEnded = PublishSubject.create();
    private final Text gameEndText = new Text();

    private List<Point2D> flowers = new ArrayList<>();

    public static void main(String[] args) {
        Application.launch(SnakeGame.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        root.setAlignment(Pos.BOTTOM_LEFT);
        Scene scene = new Scene(root);

        sky = new Canvas(SCREEN_EDGE, SCREEN_EDGE);
        root.getChildren().add(sky);

        Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(new FxPlatformScheduler())
                .takeUntil(gameEnded)
                .subscribe(tick -> advanceSnake());

        gameEndText.setFont(Font.font("Consolas", FontWeight.BOLD, 60));
        root.getChildren().add(gameEndText);
        gameEnded.subscribe(won -> gameEndText.setText("GAME " + (won ? "WON" : "OVER")));

        Observable<Point2D> flowerObservable = Observable.interval(10, TimeUnit.SECONDS)
                .startWith(1L,10L)
                .observeOn(new FxPlatformScheduler())
                .takeUntil(gameEnded)
                .map(this::randomFlower);

        Random delayRand = new Random();
        Observable<Integer> randomObservable = Observable.fromCallable(() -> 5 + delayRand.nextInt() + 10);
        flowerObservable
//                .zipWith(randomObservable, (p, d) -> new Tuple2(p, d))
//                .delay(p -> randomObservable)
                .delay(10, TimeUnit.SECONDS)
                .subscribe(this::removeFlower);
        flowerObservable
                .subscribe(this::addFlower);

        keyPresses(scene)
                .takeUntil(gameEnded)
                .subscribe(event -> {
                    switch (event.getCode()) {
                        case UP:
                            turnSnake(Direction.UP);
                            break;
                        case DOWN:
                            turnSnake(Direction.DOWN);
                            break;
                        case LEFT:
                            turnSnake(Direction.LEFT);
                            break;
                        case RIGHT:
                            turnSnake(Direction.RIGHT);
                            break;
                    }
                });

        keyPresses(scene).filter(e -> e.getCode() == KeyCode.ESCAPE)
                .subscribe(e -> System.exit(0));
        initializeSnake();

        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.show();
    }

    private void addFlower(Point2D flower) {
        flowers.add(flower);
        drawFlowerPoint(flower);
    }

    private void removeFlower(Point2D flower) {
        flowers.remove(flower);
        clearPoint(flower);
    }

    private Point2D randomFlower(Long tick) {
        Random random = new Random(tick);
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(SCREEN_EDGE / PIXEL_SIZE);
            int y = random.nextInt(SCREEN_EDGE / PIXEL_SIZE);
            Point2D p = new Point2D(x, y);
            if (!flowers.contains(p) && !snake.contains(p)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Could not generate new flower");
    }

    enum Direction {
        DOWN(0, 1),
        UP(0, -1),
        LEFT(-1, 0),
        RIGHT(1, 0);
        public final int x, y;

        Direction(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Direction getOpposite() {
            return Stream.of(values()).filter(other -> other.x == -this.x && other.y == -this.y).findFirst().get();
        }

        public Point2D move(Point2D point2D) {
            return new Point2D(point2D.getX() + x, point2D.getY() + y);
        }
    }


    Direction direction;
    Point2D head;
    LinkedList<Point2D> snake = new LinkedList<>();

    private void initializeSnake() {
        int center = SCREEN_EDGE / PIXEL_SIZE / 2;
        addSnakePoint(new Point2D(center, center - 1));
        addSnakePoint(new Point2D(center, center));
        addSnakePoint(new Point2D(center, center + 1));
        addSnakePoint(new Point2D(center, center + 2));
        head = snake.getFirst();
        direction = Direction.DOWN;
    }


    void turnSnake(Direction newDirection) {
        if (newDirection == direction.getOpposite()) {
            return; // cannot turn back
        }
        direction = newDirection;
        advanceSnake();
    }

    void advanceSnake() {
        head = direction.move(head);
        if (snake.contains(head)) {
            gameEnded.onNext(false);
            return;
        }
        addSnakePoint(head);
        removeLastSnakePoint();
    }

    private void removeLastSnakePoint() {
        Point2D lastSnakePoint = snake.removeLast();
        clearPoint(lastSnakePoint);
    }

    void addSnakePoint(Point2D point) {
        snake.addFirst(point);
        drawSnakePoint(point);
    }

    private void drawSnakePoint(Point2D point) {
        GraphicsContext g = sky.getGraphicsContext2D();
        g.setFill(Color.LIGHTGRAY);
        g.fillRect(point.getX() * PIXEL_SIZE, point.getY() * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
        sky.getGraphicsContext2D()
                .strokeRect(point.getX() * PIXEL_SIZE + 1, point.getY() * PIXEL_SIZE + 1, PIXEL_SIZE - 2, PIXEL_SIZE - 2);
    }
    private void drawFlowerPoint(Point2D point) {
        GraphicsContext g = sky.getGraphicsContext2D();
        g.setFill(Color.ORANGE);
        g.fillRect(point.getX() * PIXEL_SIZE, point.getY() * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
//        sky.getGraphicsContext2D()
//                .strokeRect(point.getX() * PIXEL_SIZE + 1, point.getY() * PIXEL_SIZE + 1, PIXEL_SIZE - 2, PIXEL_SIZE - 2);
    }

    private void clearPoint(Point2D point) {
        sky.getGraphicsContext2D()
                .clearRect(point.getX() * PIXEL_SIZE, point.getY() * PIXEL_SIZE, PIXEL_SIZE, PIXEL_SIZE);
    }

    public static Observable<KeyEvent> keyPresses(Scene scene) {
        return Observable.unsafeCreate(subscriber -> {
            EventHandler<KeyEvent> handler = subscriber::onNext;
            scene.addEventHandler(KeyEvent.KEY_PRESSED, handler);

            subscriber.add(Subscriptions.create(
                    () -> scene.removeEventHandler(KeyEvent.KEY_PRESSED, handler)));
        });
    }


}
