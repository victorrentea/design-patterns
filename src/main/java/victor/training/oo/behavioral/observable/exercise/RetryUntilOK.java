package victor.training.oo.behavioral.observable.exercise;

import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.Single;

import java.util.Random;

@Slf4j
public class RetryUntilOK {

    public static void main(String[] args) {
        // TODO Single toObservable
        // TODO handle errors
        // TODO stop at first OK response: take(1) or first()?
        Observable.range(1, 3)
                .flatMap(retry -> getDeliveryEstimation(retry).toObservable())
                .onErrorReturn(t -> new Response(1000, ""))
                .skipWhile(response -> response.getStatusCode() >= 300)
                .first() // or .take(1)
                .subscribe(System.out::println);


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