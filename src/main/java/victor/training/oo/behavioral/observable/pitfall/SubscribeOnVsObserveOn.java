package victor.training.oo.behavioral.observable.pitfall;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

@Slf4j
public class SubscribeOnVsObserveOn {
    public static void main(String[] args) {
        // TODO understand this:
        Observable.just("a")
                .subscribeOn(Schedulers.io())
                .doOnNext(log::debug)
                .observeOn(Schedulers.computation())
                .doOnNext(log::debug)
                .map(String::toUpperCase)
                .observeOn(Schedulers.io())
                .doOnNext(log::debug)
                .flatMap(SubscribeOnVsObserveOn::httpCall)
                .doOnNext(b->log.debug("ok?"+b))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .toBlocking()
                .subscribe(b->log.debug("end:"+b));
    }

    public static Observable<Boolean> httpCall(String s) {
        return Observable.just(s.equals("A")).delay(1, TimeUnit.SECONDS);
    }
}
