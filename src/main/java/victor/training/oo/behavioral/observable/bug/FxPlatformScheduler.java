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
            Subscription noAction = Subscriptions.empty();

            @Override
            public Subscription schedule(Action0 action) {
                Platform.runLater(action::call);
                return noAction;
            }

            @Override
            public Subscription schedule(Action0 action, long delayTime, TimeUnit unit) {
                try {
                    Thread.sleep(unit.toMillis(delayTime));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater(action::call);
                return noAction;
            }

            @Override
            public void unsubscribe() {
                noAction.unsubscribe();
            }

            @Override
            public boolean isUnsubscribed() {
                return noAction.isUnsubscribed();
            }
        };
    }
}
