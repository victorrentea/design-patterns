package victor.training.oo.behavioral.observable.bug;

import javafx.application.Platform;
import rx.Scheduler;
import rx.Subscription;
import rx.functions.Action0;
import rx.subscriptions.Subscriptions;

import java.util.concurrent.TimeUnit;

public class FxPlatformScheduler extends Scheduler {
    @Override
    public Worker createWorker() {
        return new Worker() {
            private final Subscription subscription = Subscriptions.create(() -> {});
            @Override
            public Subscription schedule(Action0 action) {
                Platform.runLater(() -> {
                    if (!isUnsubscribed()) action.call();
                });
                return null;

            }

            @Override
            public Subscription schedule(Action0 action, long delayTime, TimeUnit unit) {
                Platform.runLater(() -> {
                    try {
                        Thread.sleep(unit.toMillis(delayTime));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (!isUnsubscribed()) {
                        action.call();
                    }
                });
                return this;
            }

            @Override
            public void unsubscribe() {
                subscription.unsubscribe();
            }

            @Override
            public boolean isUnsubscribed() {
                return subscription.isUnsubscribed();
            }
        };
    }
}
