package victor.training.oo.behavioral.observable;

import rx.Observable;
import victor.training.oo.stuff.ThreadUtils;

import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

public class Sample {
    public static void main(String[] args) {

        IrregularEmmit.emitAtIntervals(asList(45,45,45,45,45,45,45,45,46,45,45,45,45,45,45,45,45,45,46))
                .scan("", (s, tick) -> s + "x")
                .sample(50, TimeUnit.MILLISECONDS)
                .flatMap(Sample::doSearchOverHttp)
                .subscribe(r -> System.out.println(r));

        ThreadUtils.sleep(3000);
    }

    public static Observable<String> doSearchOverHttp(String s) {
        return Observable.just("rasp la " + s)
                .delay(100, TimeUnit.MILLISECONDS);
    }
}
