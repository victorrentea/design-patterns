package victor.training.oo.behavioral.observable.pitfall;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TheMostComplexMarble {

    private static Observable<Integer> fibonacciObservable() {
        // TODO: implement an infinite fibonacci observable
        return Observable.just(null)
                .repeat()
                .scan(new int[]{1, 1}, (prev, x) -> new int[]{prev[1], prev[0] + prev[1]})
                .map(arr -> arr[0]);
    }

    public static void main(String[] args) {
        long t0 = System.currentTimeMillis();
        Observable.just(1)
                .map(n -> {
                    if (System.currentTimeMillis() - t0 > 3_000) {
                        return "data";
                    } else {
                        throw new RuntimeException("Connection Error");
                    }
                })
                // TODO retry with a fibonacci backoff delay
                .retryWhen(attempts ->
                        attempts.zipWith(fibonacciObservable(), (e, fib) -> fib)
                                .flatMap(fib -> {
                                    log.debug("Retrying after " + fib + " ms");
                                    return Observable.timer(fib, TimeUnit.MILLISECONDS);
                                }))

                .toBlocking()
                .subscribe(System.out::println);

        fibonacciObservable()
                .take(10)
                .subscribe(System.out::println);
    }


}
