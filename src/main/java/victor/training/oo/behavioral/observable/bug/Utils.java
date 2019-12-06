package victor.training.oo.behavioral.observable.bug;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import rx.Observable;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

import java.io.File;

public class Utils {
    public static Observable<KeyEvent> keyPresses(Scene scene) {
        return Observable.create(observer -> {
            EventHandler<KeyEvent> handler = event -> observer.onNext(event);
            scene.addEventHandler(KeyEvent.KEY_PRESSED, handler);
            Subscription s = Subscriptions.create(() -> {
                scene.removeEventHandler(KeyEvent.KEY_PRESSED, handler);
            });
            observer.add(s);
        });
    }

    public static Observable<KeyEvent> observeKeys(Scene scene, KeyCode space) {
        return keyPresses(scene).filter(e -> e.getCode() == space);
    }

    public static String getResourceUri(String name) {
        File file = new File("src/main/resources/bug/" + name);
        return file.toURI().toString();
    }
}
