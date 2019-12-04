package victor.training.oo.behavioral.observable;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import victor.training.oo.stuff.ConcurrencyUtil;

import java.util.concurrent.TimeUnit;

import static victor.training.oo.stuff.ConcurrencyUtil.log;
import static victor.training.oo.stuff.ConcurrencyUtil.sleep2;

public class SubscriberVsObserver {
    public static void main(String[] args) {
        Subscriber<Long> observer = new Subscriber<Long>() {
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
//                .deb
            .subscribe(observer);
        sleep2(1500);

        // observer.unsubscribe();
        log("Unsubscribed");

        sleep2(1000);
        log("END");
    }
}
