package victor.training.oo.behavioral.observable.bug;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.TestScheduler;
import rx.subjects.PublishSubject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BugsLifeGame extends Application {

    public static final int BUG_SPEED = 2;
    private final Image tileImage = new Image(getResourceUri("GrassBlock.png"));
    private static final int screenWidth = 800;
    private static final int screenHeight = 600;
    private final Text scoreText = new Text();

    public static void main(String[] args) {
        Application.launch(BugsLifeGame.class);
    }

    private final ImageView sun = new ImageView();
    private final ImageView bug = new ImageView();


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

        root.getChildren().add(createSky());


        // TODO create an observer that fires every 10ms on the computation() thread,
        //  but its subscribers (+operators) run in the GUI event loop thread



//        Scheduler scheduler = createTestScheduler(scene);

        // TODO Play a bit with these tick evens: filter, map, buffer, scan
        // TODO try prime number computation + lower period
        // TODO backpressure


        // ====== TILES =======
        List<ImageView> tiles = createTiles();
        tiles.forEach(root.getChildren()::add);

        // TODO make every tile move left with BUG_SPEED, and then return from right when out (translateX <= -width)
//                    tile.setTranslateX(screenWidth - BUG_SPEED);


        // ========== SUN ===========
        showHeart(false);
        sun.setTranslateY(-(screenHeight - 200));
        root.getChildren().add(sun);

        // TODO make sun move left with BUG_SPEED, and then return from right when out (translateX <= -width)


        // ========== BUG ===========
        double groundY = (-tileImage.getHeight() / 2) - 5;
        double gravity = 0.1;

        bug.setImage(new Image(getResourceUri("Bug.png")));
        bug.setTranslateY(groundY);
        bug.setTranslateX(screenHeight / 2);
        root.getChildren().add(bug);

        // ========== JUMPS ===========
        PublishSubject<Double> jumps = PublishSubject.create();

        // TODO jump dynamics with gravity: remember Y decreases UPWARDS

        double jumpSpeed = 8;
        // TODO on SPACE jump up with this speed
        // OPT play sound: new AudioClip(getResourceUri("smb3_jump.wav")).play()



        // TODO observable of positions: sun.localToScene(sun.getLayoutBounds());

        // TODO intersect the observable of positions, and fire only when they intersect. ONLY ONCE.
//        Observable<List<Boolean>> hitsObservable =

        // TODO turn sun into a heart for the duration of the impact: showHeart(true);


        // TODO increment and display a score: scoreText.setText("Score: " + count);


        scoreText.setFont(Font.font("Consolas", FontWeight.BOLD, 40));
        root.getChildren().add(scoreText);

        // TODO: on KeyCode.ESCAPE -> System.exit()


        stage.setOnShown(e -> new AudioClip(getResourceUri("smb3_power-up.wav")).play());
        stage.setTitle("A Bugs Life");
        stage.setScene(scene);
        stage.show();
    }

    private Scheduler createTestScheduler(Scene scene) {
        TestScheduler testScheduler = new TestScheduler();
//        observeKeys(scene, KeyCode.ENTER).subscribe(e -> {
//            testScheduler.advanceTimeBy(10000 / 60, TimeUnit.MILLISECONDS);
//        });
        return testScheduler;
    }

    private Canvas createSky() {
        Canvas sky = new Canvas(screenWidth, screenHeight);
        GraphicsContext context = sky.getGraphicsContext2D();
        context.setFill(Color.AZURE);
        context.fillRect(0, 0, screenWidth, screenHeight);
        return sky;
    }

    private List<ImageView> createTiles() {
        int nrTiles = 1 + (int) Math.ceil(screenWidth / tileImage.getWidth());
        List<ImageView> tiles = new ArrayList<>();
        for (int i = 0; i < nrTiles; i++) {
            ImageView tile = new ImageView();
            tile.setImage(tileImage);
            tile.setTranslateX(i * tileImage.getWidth());
            tiles.add(tile);
        }
        return tiles;
    }

    public void showHeart(boolean b) {
        if (b) {
            sun.setImage(new Image(getResourceUri("Heart.png")));
        } else {
            sun.setImage(new Image(getResourceUri("Star.png")));
        }
    }

    public static String getResourceUri(String name) {
        File file = new File("src/main/resources/bug/" + name);
        return file.toURI().toString();
    }

    public static Observable<KeyEvent> keyPresses(Scene scene) {
        // TODO return Observable.unsafeCreate(
        return null;
    }



}
