package victor.training.oo.behavioral.observable;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import victor.training.oo.stuff.ThreadUtils;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TimeFullOps {
    public static void main(String[] args) {

        log.debug("Start search");
        httpVrajeala(1)
                .zipWith(Observable.timer(2,TimeUnit.SECONDS),(s,zero)->s)
                .subscribe(s -> log.debug("Got results: " +s));
        // intarziem raspunsul ca sa para credibil, sa nu dureze prea putin search

        ThreadUtils.sleep(4000);
    }

    public static Observable<String> httpVrajeala(long movieId) {
        return Observable.just("a")
                .delay(3, TimeUnit.SECONDS);
    }
}
