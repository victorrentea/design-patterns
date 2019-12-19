package victor.training.oo.behavioral.observable.bug;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.Scheduler;
import rx.schedulers.TestScheduler;
import rx.subjects.PublishSubject;
import rx.subscriptions.Subscriptions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

@Slf4j
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

    public int m1() {
        return 1;
    }
    public Future<Integer> m2() {
        return CompletableFuture.completedFuture(1);
    }
//    public Stream<Future<Integer>> m4() {
//        return CompletableFuture.completedFuture(Arrays.asList(1,2,3));
//    }
    public Stream<Integer> m() {
        return Stream.of(1,2,3);
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

        root.getChildren().add(createSky());


        // TODO create an observer that fires every 10ms on the computation() thread, OK
        //  but its subscribers (+operators) run in the GUI event loop thread

        Observable<Long> clock = Observable.interval(7, TimeUnit.MILLISECONDS/*, createTestScheduler(scene)*/)
                .observeOn(new FxPlatformScheduler()); // face ca orice de mai jos sa ruleze pe threadul UNIC al GUI-u;ui care are voie sa modifice componente vizuale.
//        clock
//                .filter(n -> {
//                    log.debug("Filter");
//                    return n % 2 == 0;
//                })
//                .map(n -> n*2)
//                .subscribe(n -> log.debug(n + ""));

//        Scheduler scheduler = createTestScheduler(scene);

        // TODO Play a bit with these tick evens: filter, map, buffer, scan
        // TODO try prime number computation + lower period
        // TODO backpressure TODO


        // ====== TILES =======
        List<ImageView> tiles = createTiles();
        tiles.forEach(root.getChildren()::add);

        clock.subscribe(tick -> {
            for (ImageView tile : tiles) {
                if (tile.getTranslateX() < - tileImage.getWidth()) {
                    tile.setTranslateX(screenWidth);
                } else {
                    tile.setTranslateX(tile.getTranslateX() - BUG_SPEED);
                }
            }
        });

        // TODO make every tile move left with BUG_SPEED, and then return from right when out (translateX <= -width)
//                    tile.setTranslateX(screenWidth - BUG_SPEED);


        // ========== SUN ===========
        showHeart(false);
        sun.setTranslateY(-(screenHeight - 200));
        root.getChildren().add(sun);

        // TODO make sun move left with BUG_SPEED, and then return from right when out (translateX <= -width)
        clock.subscribe(tick -> {
            if (sun.getTranslateX() < - sun.getImage().getWidth()) {
                sun.setTranslateX(screenWidth);
            } else {
                sun.setTranslateX(sun.getTranslateX() - BUG_SPEED);
            }
        });

        // ========== BUG ===========
        double groundY = (-tileImage.getHeight() / 2) - 5;
        double gravity = 0.1;

        bug.setImage(new Image(getResourceUri("Bug.png")));
        bug.setTranslateY(groundY);
        bug.setTranslateX(screenHeight / 2);
        root.getChildren().add(bug);

        // ========== JUMPS ===========
        PublishSubject<Integer> onGround = PublishSubject.create();
        PublishSubject<Double> jumps = PublishSubject.create();

        // TODO jump dynamics with gravity: remember Y decreases UPWARDS

        jumps.subscribe(e -> System.out.println("SAR"));

//        clock
//                .scan(BigDecimal.ZERO, (bd,tick) -> bd.add(BigDecimal.valueOf(tick)))
//                .subscribe(tick -> System.out.println(tick));
        jumps
                .flatMap(v0 -> clock.scan(v0, (v,tick) -> v - gravity).takeUntil(onGround))
//                .doOnEach(dY -> System.out.println("V = " + dY.getValue()))
                .subscribe(dY -> {
                    if (bug.getTranslateY() <= groundY+dY) {
                        bug.setTranslateY(bug.getTranslateY()-dY);
                    } else {
                        onGround.onNext(1);
                        bug.setTranslateY(groundY);
                    }
                });

        double jumpSpeed = 8;
        // TODO on SPACE jump up with this speed
        // OPT play sound: new AudioClip(getResourceUri("smb3_jump.wav")).play()

        keyPresses(scene)
            .filter(e -> e.getCode() == KeyCode.SPACE)
            .filter(e -> bug.getTranslateY() == groundY)
            .subscribe(e -> jumps.onNext(jumpSpeed));


        // TODO observable of positions: sun.localToScene(sun.getLayoutBounds());
        Observable<Bounds> sunPosition = clock.map(tick -> sun.localToScene(sun.getLayoutBounds()));
        Observable<Bounds> bugPosition = clock.map(tick -> bug.localToScene(bug.getLayoutBounds()));

//        bugPosition.subscribe(p -> System.out.println(p));

        Observable<List<Boolean>> hits = Observable.combineLatest(sunPosition, bugPosition, Bounds::intersects)
                .buffer(2, 1);
        Observable<List<Boolean>> startHit = hits.filter(bb -> !bb.get(0) && bb.get(1));
        startHit
            .subscribe(b -> showHeart(true));

        hits.filter(bb -> !bb.get(1) && bb.get(0))
            .subscribe(b -> showHeart(false));


        // TODO intersect the observable of positions, and fire only when they intersect. ONLY ONCE.
//        Observable<List<Boolean>> hitsObservable =

        // TODO turn sun into a heart for the duration of the impact: showHeart(true);

        startHit
                .scan(0, (oldScore, bDegeaba) -> oldScore + 1)
                .subscribe(score -> scoreText.setText("Score: " + score));
        // TODO increment and display a score: scoreText.setText("Score: " + count);

        scoreText.setFont(Font.font("Consolas", FontWeight.BOLD, 40));
        root.getChildren().add(scoreText);

        keyPresses(scene)
                .filter(e -> e.getCode() == KeyCode.ESCAPE)
                .subscribe(e -> System.exit(2));

        stage.setOnShown(e -> new AudioClip(getResourceUri("smb3_power-up.wav")).play());
        stage.setTitle("A Bugs Life");
        stage.setScene(scene);
        stage.show();
    }

    private Scheduler createTestScheduler(Scene scene) {
        TestScheduler testScheduler = new TestScheduler();
        keyPresses(scene).filter(e -> e.getCode()==KeyCode.ENTER).subscribe(e -> {
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
        return Observable.unsafeCreate(subscriber -> {
            EventHandler<KeyEvent> handler = subscriber::onNext;
            scene.addEventHandler(KeyEvent.KEY_PRESSED, handler);

            subscriber.add(Subscriptions.create(
                    () -> scene.removeEventHandler(KeyEvent.KEY_PRESSED, handler)));
        });
    }



}
