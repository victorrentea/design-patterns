package victor.training.oo.behavioral.observable;

import rx.Observable;
import rx.observables.ConnectableObservable;
import victor.training.oo.stuff.ConcurrencyUtil;

import java.util.concurrent.TimeUnit;

public class RegularEmit {
    public static void main(String[] args) {
        ConnectableObservable<Long> connectable = Observable.interval(500, TimeUnit.MILLISECONDS).publish();
        connectable.subscribe(n -> System.out.println("item " + n));
        connectable.scan(0L, Long::sum).subscribe(n -> System.out.println("sum  "+n));;

        ConcurrencyUtil.sleep2(1000);
        connectable.connect();
        ConcurrencyUtil.sleep2(3000);
    }
}
