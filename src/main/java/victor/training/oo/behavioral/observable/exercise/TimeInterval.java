package victor.training.oo.behavioral.observable.exercise;

import lombok.extern.slf4j.Slf4j;
import rx.Single;
import victor.training.oo.stuff.ThreadUtils;

import java.util.concurrent.TimeUnit;

@Slf4j
public class TimeInterval
{
    public static void main(String[] args) {

        Single.just("b")
                .subscribe(v -> log.debug(v));

        Single.just("a").delay(1, TimeUnit.SECONDS)
                .timeout(500, TimeUnit.MILLISECONDS)
//                .doOnEach(bou -> ThreadUtils.sleep(2000))
                .onErrorReturn(t -> "error")
                .map(s -> {
                    log.debug("Rulez");
                    return s.toUpperCase();
                })
                .subscribe(System.out::println, t -> log.error(t.getMessage(),t));

        ThreadUtils.sleep(4000);
    }
}
