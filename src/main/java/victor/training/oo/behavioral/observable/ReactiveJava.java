package victor.training.oo.behavioral.observable;


import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import victor.training.oo.stuff.ConcurrencyUtil;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toList;

@Slf4j
public class ReactiveJava {
    static Object result = "";
    public static void main(String[] args) {
//        Observable<String> observable = Observable.just("Hello");
        Observable<Integer> observable = Observable
            .from(Arrays.asList("1","2"/*,"c"*/,"4"))
            .flatMap(s1 -> expensiveParse(s1));
//        ExecutorService pool = Executors.newFixedThreadPool(2);
//        Future<String> future = pool.submit(() -> {
//            ConcurrencyUtil.sleep2(1000);
//            return "wohoo";
//        });
//        Observable<String> observable = Observable.from(future);

        log.debug("Sumitted task");
        System.out.println(observable.getClass());
        log.debug("created observable");
        observable
                .scan(0, Integer::sum)
        .subscribe(s -> {
            log.debug("set " + s);
            result = s;
        }, throwable -> log.error("ERR", throwable),
           () -> log.info("DONE") );
        log.debug("OUT: " + result);


        Observable.from(IntStream.rangeClosed(1,10).boxed().collect(toList()))
                .takeWhile(i -> i < 8)
            .groupBy(i -> i % 2 ==0 ? "EVEN" : "ODD")
            .subscribe(group ->
                group.subscribe(number -> {
                    if (group.getKey().toString().equals("EVEN")) {
                        even += number;
                    } else {
                        odd += number;
                    }
                }));
        log.debug("Even: " + even);
        log.debug("Odd: " + odd);
    }
    static String even ="", odd ="";

    private static Observable<Integer> expensiveParse(String s1) {
        return Observable.just(Integer.parseInt(s1));
    }
}
