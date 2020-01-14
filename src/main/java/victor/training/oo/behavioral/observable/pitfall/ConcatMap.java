package victor.training.oo.behavioral.observable.pitfall;

import rx.Observable;

import java.util.concurrent.TimeUnit;

public class ConcatMap {
    public static void main(String[] args) {
        Observable.range(1, 3)
            .flatMap(i -> Observable.interval(100, TimeUnit.MILLISECONDS).take(5))
            .toBlocking()
            .subscribe(System.out::println);
    }
}
