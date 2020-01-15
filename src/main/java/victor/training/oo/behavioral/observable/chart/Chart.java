package victor.training.oo.behavioral.observable.chart;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import rx.Observable;
import rx.subscriptions.Subscriptions;

import java.util.concurrent.TimeUnit;

public class Chart extends Application {
    private static final int screenWidth = 800;
    private static final int screenHeight = 600;
    public static final int Y0 = screenHeight - 20;
    public static final int X0 = 20;
    private static final int MAXY = 200;
    public static final double sampleRate = 20;
    private Canvas canvas;
    private long t0 = System.currentTimeMillis();

    public static void main(String[] args) {
        Application.launch(Chart.class);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //  ^
        //  |
        // -dy
        //  |
        // (0,0) -----dx--->
        StackPane root = new StackPane();
        root.setAlignment(Pos.BOTTOM_LEFT);
        Scene scene = new Scene(root);


        canvas = new Canvas(screenWidth, screenHeight);
        GraphicsContext context = canvas.getGraphicsContext2D();
        context.setFill(Color.LIGHTGRAY);
        context.fillRect(0, 0, screenWidth, screenHeight);

        context.setStroke(Color.BLACK);
        context.strokeLine(20, Y0,20,20);
        context.strokeLine(X0,Y0,screenWidth-20,Y0);

        int time = 1000;
        int value = 50;


        Color color = Color.RED;

        drawPoint(value, color);

        keyPresses(scene)
//            .debounce(100, TimeUnit.MILLISECONDS)
//            .sample(100, TimeUnit.MILLISECONDS)
                .throttleFirst(100, TimeUnit.MILLISECONDS)
            .subscribe(keyEvent -> drawPoint(keyEvent.getCode().ordinal(), Color.BLUE));
        keyPresses(scene)
//            .debounce(100, TimeUnit.MILLISECONDS)
//            .sample(100, TimeUnit.MILLISECONDS)
                .map(e -> 1)
                .scan(1, Integer::sum)
                .throttleLast(100, TimeUnit.MILLISECONDS)
            .subscribe(v -> drawPoint(v-1, Color.GREEN));

        keyPresses(scene)
            .subscribe(keyEvent -> drawPoint(keyEvent.getCode().ordinal() + 1, Color.RED));



        Observable.interval(100, TimeUnit.MILLISECONDS)
                .observeOn(new FxPlatformScheduler())
//                .takeUntil(tick -> tick > 10 )
                .takeUntil(keyPresses(scene).filter(e ->e.getCode() == KeyCode.ESCAPE))
                .subscribe(tick -> drawPoint(tick, Color.BLACK));



        root.getChildren().add(canvas);

        stage.setTitle("Charting");
        stage.setScene(scene);
        stage.show();
    }

    private void drawPoint(long value, Color color) {
        GraphicsContext context = canvas.getGraphicsContext2D();
        int time = (int) ((int) ((System.currentTimeMillis() - t0) / sampleRate) );
        int x = X0 + time;
        int y = (int) (Y0 - value * (screenHeight - 2*20) / MAXY) - 20;
        if (x < 0 || y < 0 || x > screenWidth || y > screenHeight) {
            System.err.println("Point outbound at ("+x+","+y+")");
        }
        context.setFill(color);
        context.fillRect(x,y,2,2);
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
