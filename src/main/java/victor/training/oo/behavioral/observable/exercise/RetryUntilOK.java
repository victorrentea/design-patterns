package victor.training.oo.behavioral.observable.exercise;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.Single;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RetryUntilOK {

    public static void main(String[] args) {
        // TODO Single toObservable
        // TODO handle errors
        // TODO stop at first OK response: take(1) or first()?

        log.debug("Start");
        Observable.range(1, 3)
            .zipWith(Observable.interval(0, 1, TimeUnit.SECONDS), (i,t) -> i)
            .flatMap(retryIndex ->getDeliveryEstimation(retryIndex)
                        .toObservable()
                        .onErrorReturn(t->new Response(10000, null)))
            .takeUntil(response -> response.getStatusCode() == 200)
            .filter(response -> response.getStatusCode() == 200)
            .first()
            .toBlocking()
            .subscribe(response -> System.out.println("Dau aplicatiei " + response));

    }

    public static Single<Response> getDeliveryEstimation(int retryIndex) {
        log.debug("GET /url/order?retry=" + retryIndex);
        switch (new Random().nextInt(3)) {
            // TODO try throwing errors (set 3 as bound above) -> kills the Observable
            case 0:
                log.debug("Return 200");
                return Single.just(new Response(200, "DATA"));
            case 1:
                log.debug("Return 500");
                return Single.just(new Response(500, ""));
            case 2:
                log.debug("Return Timeout");
                return Single.error(new RuntimeException("Connection timed out"));
            default:
                throw new IllegalStateException("Unexpected value: " + new Random().nextInt(3));
        }
    }
}

@Value
class Response {
    int statusCode;
    String body;
}