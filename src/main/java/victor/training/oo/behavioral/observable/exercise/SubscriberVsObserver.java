package victor.training.oo.behavioral.observable.exercise;

import rx.Observable;
import rx.Subscriber;

import java.util.concurrent.TimeUnit;

import static victor.training.oo.stuff.ConcurrencyUtil.log;
import static victor.training.oo.stuff.ConcurrencyUtil.sleep2;

public class SubscriberVsObserver {
    public static void main(String[] args) {
        Subscriber<Long> subscriber = new Subscriber<Long>() {
            @Override
            public void onCompleted() {
                log("DONE");
            }

            @Override
            public void onError(Throwable e) {
                log("ERR");
            }

            @Override
            public void onNext(Long aLong) {
                log("elem " + aLong);
            }
        };
        Observable.interval(500, TimeUnit.MILLISECONDS)
            .map(n -> {
                log("Square " + n);
                return n * n;
            })
            .subscribe(subscriber);
        sleep2(1600);


        subscriber.unsubscribe(); // TODO switch to Observer and try this again
        log("Unsubscribed");

        sleep2(1000);
        log("END");
    }
}
