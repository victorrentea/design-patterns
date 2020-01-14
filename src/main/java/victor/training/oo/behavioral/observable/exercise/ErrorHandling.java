package victor.training.oo.behavioral.observable.exercise;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import victor.training.oo.stuff.ThreadUtils;

import java.util.concurrent.TimeUnit;

@Slf4j
public class ErrorHandling {
    public static void main(String[] args) {

        Observable
                .interval(100, TimeUnit.MILLISECONDS)
                .doOnSubscribe(() -> {
                    System.out.println("(re)subscribe");
                })
                .map(tick -> {
                    if (Math.random()<0.2){
                        throw new IllegalArgumentException();
                    }
                    return 100+tick;
                })

                // TODO retry
                // TODO onError end with value
                // TODO onError continue with the seq 10 9 8 7 6 .. 1, at 100 ms between them
                // SOLUTION:
//                .onErrorReturn(t -> -1)
//                .retry()
//                .onErrorResumeNext(Observable.interval(100, TimeUnit.MILLISECONDS).take(10).map(i -> 10L - i))
                .subscribe(i -> log.debug("got " + i));


        ThreadUtils.sleep(3000);
    }
}
