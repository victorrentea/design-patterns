package victor.training.oo.behavioral.observable;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;

@Slf4j
public class IrregularEmmit {
    public static void main(String[] args) {
        List<Integer> delays = asList(100, 100, 200, 300, 100, 200);


        emitAtIntervals(delays).subscribe(tick -> log.debug("tick"));

    }

    public static Observable<Long> emitAtIntervals(List<Integer> delays) {
        return Observable.from(delays)
                .scan(0, Integer::sum)
                .skip(1)
                .flatMap(delay -> {
//                    log.debug("Oare cand creeaza " + delay);
                    return Observable.timer(delay, TimeUnit.MILLISECONDS);
                });
    }
}
