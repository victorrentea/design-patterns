package victor.training.oo.behavioral.observable.exercise;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import victor.training.oo.stuff.ConcurrencyUtil;

import java.util.concurrent.TimeUnit;

@Slf4j
public class Connectable {
    public static void main(String[] args) {
        log.debug("Start");
        Observable<Long> connectable = Observable
                .interval(500, TimeUnit.MILLISECONDS)
//                .publish()
                ;
        connectable.subscribe(n -> log.debug("item " + n));
        connectable.scan(0L, Long::sum).subscribe(n -> log.debug("sum  "+n));;

        // TODO Don't yet allow items to be published in the first 2 seconds
        ConcurrencyUtil.sleep2(2000);
        log.debug("Connect");
//        connectable.connect();
        ConcurrencyUtil.sleep2(3000);
    }
}
