package victor.training.oo.behavioral.observable.single;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.Single;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Single1 {
    public static void main(String[] args) {

        Single<Integer> single = Single.just(1);

        single.flatMapObservable(i -> Observable.just(i).repeat())
                .take(10)
                .subscribe(System.out::println);


        log.debug("Fire");
        Single<Integer> s1 = Single.just(5).delay(1, TimeUnit.SECONDS);
        Single<Integer> s2 = Single.just(10).delay(2, TimeUnit.SECONDS);

        Single.merge(s1, s2)
                .toBlocking()
                .subscribe(n -> log.debug("" + n));
    }
}
