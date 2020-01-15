package victor.training.oo.behavioral.observable.pitfall;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import victor.training.oo.stuff.ThreadUtils;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TheMostComplexMarble {

    private static Observable<Integer> fibonacciObservable() {
        // TODO: implement an infinite fibonacci observable
        return Observable.just(1)
                .repeat()
                .scan(new int[]{1,1}, (arr, i) -> new int[]{arr[1], arr[0] + arr[1]})
                .map(arr -> arr[0]);
    }

    public static void main(String[] args) {
//        fibonacciObservable()
//                .take(100)
//                .subscribe(System.out::println);


//        Observable.defer(() -> Observable.just(f()))
//        Observable.fromCallable(() -> f())
////                .subscribeOn(Schedulers.io())
//                .subscribe(System.out::println);
//
//        ThreadUtils.sleep(1000);

//        fibonacciObservable()
//                .take(10)
//                .scan(0, Integer::sum)
//                .flatMap(fib -> Observable.timer(fib, TimeUnit.MILLISECONDS).map(t->fib))
//                .subscribe(v -> log.debug("valXX " + v));



        long t0 = System.currentTimeMillis();
        Observable.defer(() -> {
                    log.debug("Calling you...");
                    if (System.currentTimeMillis() - t0 > 3_000) {
                        return Observable.just("data");
                    } else {
                        return Observable.error(new RuntimeException("Connection Error"));
                    }
                })
                // TODO retry with a fibonacci backoff delay
                .retryWhen(failures -> {
                    System.out.println("Ruleaza 1 DATA!");

//                    Observable<Long> latente = Observable.interval(100, TimeUnit.MILLISECONDS);
                    Observable<Integer> latente = fibonacciObservable()
                            .take(100)
                            .scan(0, Integer::sum)
                            .flatMap(fib -> Observable.timer(fib, TimeUnit.MILLISECONDS).map(t -> fib));

                    return failures.zipWith(latente, (e, t)->t);
                })

                .subscribe(System.out::println);


        ThreadUtils.sleep(5000);
    }

    private static int f() {
        log.debug("Generez");
        return 1;
    }


}
