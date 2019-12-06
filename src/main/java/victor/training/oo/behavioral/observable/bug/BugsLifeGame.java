package victor.training.oo.behavioral.observable.bug;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import rx.schedulers.TestScheduler;
import rx.subjects.PublishSubject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BugsLifeGame extends Application {

    private final Image tileImage = new Image(Utils.getResourceUri("GrassBlock.png"));
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


        Scheduler scheduler = Schedulers.computation();
//        Scheduler scheduler = createTestScheduler(scene);
        Observable<Long> clock = Observable.interval(0, 400 / 60, TimeUnit.MILLISECONDS, scheduler)
                .observeOn(new PlatformScheduler());


        // ====== TILES =======
        List<ImageView> tiles = createTiles();
        tiles.forEach(root.getChildren()::add);
        clock.scan(1, (dx, a) -> dx).subscribe(dX -> {
            for (ImageView tile : tiles) {
                if (tile.getTranslateX() <= -tile.getImage().getWidth()) {
                    tile.setTranslateX(screenWidth - dX);
                } else {
                    tile.setTranslateX(tile.getTranslateX() - dX);
                }
            }
        });


        // ========== SUN ===========
        showHeart(false);
        sun.setTranslateY(-(screenHeight - 200));
        root.getChildren().add(sun);

        clock.scan(1, (dx, a) -> dx).subscribe(dX -> {
            if (sun.getTranslateX() <= -sun.getImage().getWidth()) {
                sun.setTranslateX(screenWidth - dX);
            } else {
                sun.setTranslateX(sun.getTranslateX() - dX);
            }
        });


        // ========== BUG ===========
        double groundY = (-tileImage.getHeight() / 2) - 5;
        double gravity = 0.1;

        bug.setImage(new Image(Utils.getResourceUri("Bug.png")));
        bug.setTranslateY(groundY);
        bug.setTranslateX(screenHeight / 2);
        root.getChildren().add(bug);

        // ========== JUMPS ===========
        PublishSubject<Double> jumps = PublishSubject.create();

        jumps.flatMap(v0 -> clock.scan(v0, (v, tick) -> v - gravity).takeUntil(jumps))
                .subscribe(dy -> {
                    if (bug.getTranslateY() < groundY + dy) {
                        bug.setTranslateY(bug.getTranslateY() - dy);
                    } else {
                        bug.setTranslateY(groundY);
                    }
                });

        double jumpSpeed = 8;
        Utils.observeKeys(scene, KeyCode.SPACE)
                .filter(e -> bug.getTranslateY() >= groundY)
                .doOnEach(e -> new AudioClip(Utils.getResourceUri("smb3_jump.wav")).play())
                .subscribe(e -> jumps.onNext(jumpSpeed));


        Observable<Bounds> heartBoundingBoxes = clock.map(t -> sun.localToScene(sun.getLayoutBounds()));
        Observable<Bounds> bugBoundingBoxes = clock.map(t -> bug.localToScene(bug.getLayoutBounds()));

        Observable<List<Boolean>> s = Observable.combineLatest(bugBoundingBoxes, heartBoundingBoxes, (bug, heart) -> bug.intersects(heart))
                .buffer(2, 1)
                .filter(hits -> hits.get(0) != hits.get(1));
        s.subscribe(hits -> {
            if (!hits.get(0)) {
                showHeart(true);
                new AudioClip(Utils.getResourceUri("smb3_coin.wav"));
            } else {
                showHeart(false);
            }
        });
        s.filter(hits -> !hits.get(0))
                .scan(0, (count, bb) -> count + 1)
                .subscribe(count -> {
                    scoreText.setText("Score: " + count);
                    System.out.println("Score: " + count);
                });

        scoreText.setFont(Font.font("Consolas", FontWeight.BOLD, 40));
        root.getChildren().add(scoreText);

        Utils.observeKeys(scene, KeyCode.ESCAPE).subscribe(e -> System.exit(1));

        stage.setOnShown(e -> new AudioClip(Utils.getResourceUri("smb3_power-up.wav")).play());
        stage.setTitle("A Bugs Life");
        stage.setScene(scene);
        stage.show();
    }

    private Scheduler createTestScheduler(Scene scene) {
        TestScheduler testScheduler = new TestScheduler();
        Utils.observeKeys(scene, KeyCode.ENTER).subscribe(e -> {
            testScheduler.advanceTimeBy(10000 / 60, TimeUnit.MILLISECONDS);
        });
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
            sun.setImage(new Image(Utils.getResourceUri("Heart.png")));
        } else {
            sun.setImage(new Image(Utils.getResourceUri("Star.png")));
        }
    }

}
