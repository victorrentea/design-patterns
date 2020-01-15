package victor.training.oo.behavioral.observable;

import rx.Observable;
import rx.Single;
import victor.training.oo.stuff.ThreadUtils;

import java.util.concurrent.TimeUnit;

public class Merge {

    public static void main(String[] args) {

        Single<String> s1 = Single.just("a").delay(1, TimeUnit.SECONDS);
        Single<Integer> s2 = Single.just(3).delay(2, TimeUnit.SECONDS);

        s1.subscribe(System.out::println);
        s2.subscribe(System.out::println);

        Single.merge(s1,s2)
                .toCompletable()
                .subscribe(() -> System.out.println("Gata!"));

        Single.merge(s1,s2)
                .subscribe(data -> System.out.println("A venit pe teava. Tratez uniform " + data));



        Observable<Long> o1 = Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(3)
                .map(tick -> -tick);
        Observable<Long> o2 = Observable.interval(100, TimeUnit.MILLISECONDS)
                .take(3);

        o1.mergeWith(o2).subscribe(System.out::println);
        o1.concatWith(o2).subscribe(i -> System.out.println("Concat:"+i));


        ThreadUtils.sleep(5000);
    }
}
