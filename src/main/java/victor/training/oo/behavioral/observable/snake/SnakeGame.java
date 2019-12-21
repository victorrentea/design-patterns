package victor.training.oo.behavioral.observable.snake;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subscriptions.Subscriptions;
import victor.training.oo.behavioral.observable.bug.FxPlatformScheduler;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SnakeGame extends Application {

    public static final int SCREEN_EDGE = 800;
    public static final int NO_PIXELS = 20;
    private Board board;
    private PublishSubject<Boolean> gameEnded = PublishSubject.create();
    private final Text gameEndText = new Text();
    private final Text scoreText = new Text();

    private List<Point> flowers = new ArrayList<>();
    private final PublishSubject<Long> flowerCreate = PublishSubject.create();
    private int timeLeft = 50;

    public static void main(String[] args) {
        Application.launch(SnakeGame.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        StackPane root = new StackPane();
        Scene scene = new Scene(root);

        Canvas canvas = new Canvas(SCREEN_EDGE, SCREEN_EDGE);
        root.getChildren().add(canvas);
        board = new Board(canvas, NO_PIXELS);

        Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(new FxPlatformScheduler())
                .takeUntil(gameEnded)
                .subscribe(tick -> advanceSnake());

        gameEndText.setFont(Font.font("Consolas", FontWeight.BOLD, 60));
        root.getChildren().add(gameEndText);
        scoreText.setFont(Font.font("Consolas", FontWeight.BOLD, 30));
        root.getChildren().add(scoreText);
        root.setAlignment(Pos.BOTTOM_LEFT);

        gameEnded.subscribe(won -> gameEndText.setText("GAME " + (won ? "WON" : "OVER")));

        Observable<Point> flowerObservable = flowerCreate
                .takeUntil(gameEnded)
                .map(this::randomFlower);

        Observable.interval(10, TimeUnit.SECONDS)
                .startWith(1L, 10L)
                .observeOn(new FxPlatformScheduler())
                .subscribe(flowerCreate::onNext);

        Observable.interval(1, TimeUnit.SECONDS)
                .observeOn(new FxPlatformScheduler())
                .subscribe(tick -> {
                    timeLeft --;
                    refreshScore();
                });

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
                .subscribe(event -> Direction.fromKeyCode(event.getCode()).ifPresent(this::turnSnake));

        keyPresses(scene).filter(e -> e.getCode() == KeyCode.ESCAPE)
                .subscribe(e -> System.exit(0));
        initializeSnake();

        stage.setTitle("Snake");
        stage.setScene(scene);
        stage.show();
    }

    private void addFlower(Point flower) {
        flowers.add(flower);
        board.drawFlower(flower);
    }

    private void removeFlower(Point flower) {
        if (flowers.remove(flower)) {
            board.clearPoint(flower);
        }
    }

    private Point randomFlower(Long tick) {
        Random random = new Random(tick);
        for (int i = 0; i < 100; i++) {
            int x = random.nextInt(board.getEdgeSize());
            int y = random.nextInt(board.getEdgeSize());
            Point p = new Point(x, y);
            if (!flowers.contains(p) && !snake.contains(p)) {
                return p;
            }
        }
        throw new IllegalArgumentException("Could not generate new flower");
    }


    Direction direction;
    Point head;
    LinkedList<Point> snake = new LinkedList<>();

    private void initializeSnake() {
        int center = board.getEdgeSize() / 2;
        addSnakePoint(new Point(center, center - 1));
        addSnakePoint(new Point(center, center));
        addSnakePoint(new Point(center, center + 1));
        addSnakePoint(new Point(center, center + 2));
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
        if (!board.containsPoint(head)) {
            gameEnded.onNext(false);
            return;
        }
        boolean ateFlower = flowers.remove(head);
        addSnakePoint(head);
        if (!ateFlower) {
            removeLastSnakePoint();
        } else {
            timeLeft += 10;
            refreshScore();
            flowerCreate.onNext(0L);
        }
    }

    private void refreshScore() {
        scoreText.setText("Length: " + snake.size() + ", Time Left: " + timeLeft);
    }

    private void removeLastSnakePoint() {
        Point lastSnakePoint = snake.removeLast();
        board.clearPoint(lastSnakePoint);
    }

    void addSnakePoint(Point point) {
        snake.addFirst(point);
        board.drawSnakePoint(point);
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

