package victor.training.oo.behavioral.observable;

import rx.Observable;

import java.util.concurrent.TimeUnit;

public class AndThenWhen {
    public static void main(String[] args) {
        Observable<String> chars = Observable.interval(1, TimeUnit.SECONDS)
                .map(n -> ('a' + (int) (long) n) + "");

        Observable<Long> numbers = Observable.interval(1, TimeUnit.SECONDS);

    }
}
