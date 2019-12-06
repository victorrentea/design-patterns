package victor.training.oo.behavioral.observable.bug;

import javafx.application.Application;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.Schedulers;
import rx.schedulers.TestScheduler;
import rx.subjects.PublishSubject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class BugsLifeGame extends Application {

    public static void main(String[] args) {
        Application.launch(BugsLifeGame.class);
    }

    private String getResourceUri(String name) {
        File file = new File("src/main/resources/bug/" + name);
        return file.toURI().toString();
    }

    //    private final Scheduler scheduler = new TestScheduler();
    private final Scheduler scheduler = Schedulers.computation();
    private final ImageView sun = new ImageView();
    private final ImageView bug = new ImageView();

    @Override
    public void start(Stage stage) throws Exception {
        int screenWidth = 800;
        int screenHeight = 600;
        //  ^
        //  |
        // -dy
        //  |
        // (0,0) -----dx--->
        StackPane root = new StackPane();
        root.setAlignment(Pos.BOTTOM_LEFT);
        Scene scene = new Scene(root);

        Observable<Long> clock = Observable.interval(0, 400 / 60, TimeUnit.MILLISECONDS, scheduler)
                .observeOn(new PlatformScheduler());

        Canvas sky = makeSky(screenWidth, screenHeight);
        root.getChildren().add(sky);

        Image tileImage = new Image(getResourceUri("GrassBlock.png"));
        int nrTiles = 1 + (int) Math.ceil(screenWidth / tileImage.getWidth());
        List<ImageView> tiles = new ArrayList<>();
        for (int i = 0; i < nrTiles; i++) {
            ImageView tile = new ImageView();
            tile.setImage(tileImage);
            tile.setTranslateX(i * tileImage.getWidth());
            root.getChildren().add(tile);
            tiles.add(tile);
        }

        clock.scan(1, (dX, a) -> dX).subscribe(dX -> {
            for (ImageView tile : tiles) {
                if (tile.getTranslateX() <= -tile.getImage().getWidth()) {
                    tile.setTranslateX(screenWidth - dX);
                } else {
                    tile.setTranslateX(tile.getTranslateX() - dX);
                }
            }
        });


        showHeart(false);
        sun.setTranslateY(-(screenHeight - 200));
        root.getChildren().add(sun);
        clock.scan(1, (dX, a) -> dX).subscribe(dX -> {
            if (sun.getTranslateX() <= -sun.getImage().getWidth()) {
                sun.setTranslateX(screenWidth - dX);
            } else {
                sun.setTranslateX(sun.getTranslateX() - dX);
            }
        });


        double homeY = (-tileImage.getHeight() / 2) - 5;
        double gravity = 0.1;

        bug.setImage(new Image(getResourceUri("EnemyBug.png")));
        bug.setTranslateY(homeY);
        bug.setTranslateX(screenHeight / 2);
        root.getChildren().add(bug);

        PublishSubject<Double> jumps = PublishSubject.create();

        jumps.flatMap(v0 -> clock.scan(v0, (dY, a) -> dY - gravity)
                .takeUntil(jumps)).subscribe(dy -> {
            double y;
            if (bug.getTranslateY() < homeY + dy) {
                y = bug.getTranslateY() - dy;
            } else {
                y = homeY;
            }
            bug.setTranslateY(y);
        });

        double jumpSpeed = 8;
        Utils.spaceBars(scene)
                .filter(e -> bug.getTranslateY() >= homeY)
                .doOnEach(e -> new AudioClip(getResourceUri("smb3_jump.wav")).play())
                .subscribe(e -> jumps.onNext(jumpSpeed));

        Utils.enterKeys(scene).subscribe(e -> {
            if (scheduler instanceof TestScheduler) {
                TestScheduler testScheduler = (TestScheduler) scheduler;
                testScheduler.advanceTimeBy(10000 / 60, TimeUnit.MILLISECONDS);
            }
        });

        Observable<Bounds> heartBoundingBoxes = clock.map(t -> sun.localToScene(sun.getLayoutBounds()));
        Observable<Bounds> bugBoundingBoxes = clock.map(t -> bug.localToScene(bug.getLayoutBounds()));

        Observable.combineLatest(bugBoundingBoxes, heartBoundingBoxes, (bug, heart) -> bug.intersects(heart))
                .buffer(2, 1)
                .filter(hits -> hits.get(0) != hits.get(1))
                .subscribe(hits -> {
                    if (!hits.get(0)) {
                        showHeart(true);
                        new AudioClip(getResourceUri("smb3_coin.wav"));
                    } else {
                        showHeart(false);
                    }
                });

        stage.setOnShown(e -> new AudioClip(getResourceUri("smb3_power-up.wav")).play());
        stage.setTitle("A Bugs Life");
        stage.setScene(scene);
        stage.show();
    }

    public void showHeart(boolean b) {
        if (b) {
            sun.setImage(new Image(getResourceUri("Heart.png")));
        } else {
            sun.setImage(new Image(getResourceUri("Star.png")));
        }
    }

    private Canvas makeSky(int screenWidth, int screenHeight) {
        Canvas sky = new Canvas(screenWidth, screenHeight);
        GraphicsContext context = sky.getGraphicsContext2D();
        context.setFill(Color.AZURE);
        context.fillRect(0, 0, screenWidth, screenHeight);
        return sky;
    }
}
