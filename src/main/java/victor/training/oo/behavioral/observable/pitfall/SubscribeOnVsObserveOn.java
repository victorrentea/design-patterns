package victor.training.oo.behavioral.observable.pitfall;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.schedulers.Schedulers;
import victor.training.oo.stuff.ThreadUtils;

import java.util.concurrent.TimeUnit;

@Slf4j
public class SubscribeOnVsObserveOn {
    public static void main(String[] args) {

//        Observable.defer(() -> f())
//                .subscribeOn(Schedulers.computation())
//                .observeOn(Schedulers.io())
//                .doOnNext(log::info)
//                .subscribeOn(Schedulers.io())
//                .subscribe(log::debug);
//        ThreadUtils.sleep(1000);
//
//
//        // TODO understand this:
        Observable.defer(() -> f()) // io
                .subscribeOn(Schedulers.io())
                .doOnNext(log::debug) // io
                .observeOn(Schedulers.computation())
                .doOnNext(log::debug) //com
                .map(String::toUpperCase)
                .observeOn(Schedulers.io())
                .doOnNext(log::debug) // io
                .flatMap(SubscribeOnVsObserveOn::httpCall) // io
                .doOnNext(b->log.debug("ok?"+b)) // io GRESIT: era comput
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.newThread())
                .doOnNext(x -> log.debug("" + x))
                .toBlocking()
                .subscribe(b->log.debug("end:"+b)); //new GRESIT: era main
        ThreadUtils.sleep(2000);
    }

    private static Observable<String> f() {
        log.debug("Generez");
        return Observable.just("a");
    }

    public static Observable<Boolean> httpCall(String s) {
        log.debug("Lansez HTTP");
        return Observable.just(s.equals("A"))
                .delay(1, TimeUnit.SECONDS);
    }
}
