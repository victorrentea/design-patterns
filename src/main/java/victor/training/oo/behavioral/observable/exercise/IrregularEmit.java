package victor.training.oo.behavioral.observable.exercise;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import victor.training.oo.stuff.ConcurrencyUtil;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class IrregularEmit {

    public static void main(String[] args) {
        List<Integer> deltas = Arrays.asList(100, 200, 300, 400, 500, 1000, 2000);
        Observable.from(deltas)
                 // SOLUTION:
                .scan(0, Integer::sum)
                .flatMap(d -> Observable.just(d).delay(d, TimeUnit.MILLISECONDS))
                .timeout(450, TimeUnit.MILLISECONDS, Observable.just(-1))

                .subscribe(d -> log.debug("Tick")); // TODO 1 print these deltas at waiting the above time between them
        // TODO 2: if in 1200 ms no response comes, end with a default value -1

        ConcurrencyUtil.sleep2(5000);



    }
}
