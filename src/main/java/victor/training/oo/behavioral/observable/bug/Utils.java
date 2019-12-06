package victor.training.oo.behavioral.observable.bug;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import rx.Observable;
import rx.Subscription;
import rx.subscriptions.Subscriptions;

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

    public static Observable<KeyEvent> enterKeys(Scene scene) {
        return keyPresses(scene).filter(e -> e.getCode() == KeyCode.ENTER);
    }
    public static Observable<KeyEvent> spaceBars(Scene scene) {
        return keyPresses(scene).filter(e -> e.getCode() == KeyCode.SPACE);
    }
}
