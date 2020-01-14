package victor.training.oo.behavioral.observable;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.Subscriber;
import victor.training.oo.stuff.ThreadUtils;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ObserverVsSubscriber {


    public static void main(String[] args) {

//        Observable<Integer> numbers = Observable.from(Arrays.asList(1, 2, 3, 4, 5, 6));

        Subscriber<Long> subscriber = new Subscriber<Long>() {
            public void onCompleted() {
            }

            public void onError(Throwable e) {
            }

            public void onNext(Long integer) {
                log.debug("got " + integer);
            }
        };

//        numbers.subscribe(subscriber);

//        subscriber.unsubscribe();

        Observable<Long> ceas = Observable.interval(500, TimeUnit.MILLISECONDS)
                .map(n -> {
                    log.debug("Dublez " + n);
                    return n * 2;
                });

        ceas.subscribe(subscriber);
        ceas.subscribe(tick -> log.debug("Ora" + tick)); // orice subscriber tine in viata sursa.

        ThreadUtils.sleep(3000);

        log.debug("Ma unsubscriu");
        subscriber.unsubscribe();
        //aici observableul nu mai emite caci nu mai are nici un subscriber




        ThreadUtils.sleep(3000);

    }
}
