package victor.training.oo.behavioral.observable;

import rx.Observable;

import java.util.concurrent.TimeUnit;

public class Throttle {
    public static void main(String[] args) {

        Observable.interval(100, TimeUnit.MILLISECONDS)
            .throttleFirst(250, TimeUnit.MILLISECONDS)
                .toBlocking()
            .subscribe(System.out::println);
    }
}
