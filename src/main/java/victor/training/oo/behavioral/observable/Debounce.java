package victor.training.oo.behavioral.observable;

import rx.Observable;
import victor.training.oo.stuff.ThreadUtils;

import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

public class Debounce {
    public static void main(String[] args) {

        IrregularEmmit.emitAtIntervals(asList(1, 1, 1, 1, 1, 1, 1, 1, 100, 1, 1, 1, 1, 1, 100, 1000))
                .scan("", (s, tick) -> s + "x")
                .debounce(50, TimeUnit.MILLISECONDS)
                .flatMap(Debounce::doSearchOverHttp)
                .subscribe(r -> System.out.println(r));

        ThreadUtils.sleep(3000);
    }

    public static Observable<String> doSearchOverHttp(String s) {
        return Observable.just("rasp la " + s)
                .delay(100, TimeUnit.MILLISECONDS);
    }
}
