package victor.training.oo.behavioral.observable.pitfall;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;

import static victor.training.oo.stuff.ThreadUtils.sleep;

@Slf4j
public class SharingRequests {
    public static void main(String[] args) {
        log.debug("Start");

        Observable<String> call = httpCallObs();
        // TODO avoid doing two expensive network calls
        call.subscribe(log::debug);
        call.subscribe(log::debug);
    }

    private static Observable<String> httpCallObs() {
        return Observable.fromCallable(() -> {
            log.debug("Sent HTTP Request. Waiting for response");
            sleep(1000);
            log.debug("Got Response.");
            return "response";
        });
    }
}
