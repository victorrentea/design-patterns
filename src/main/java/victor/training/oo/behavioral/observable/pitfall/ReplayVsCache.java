package victor.training.oo.behavioral.observable.pitfall;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;

import static victor.training.oo.stuff.ThreadUtils.sleep;

@Slf4j
public class ReplayVsCache {
    public static void main(String[] args) {
       Observable<String> request = Observable.defer(() -> Observable.just(httpCallObs()))
               // TODO play
                .cache();
//                .replay(); // autoConnect
        log.debug("Cached the obs. Subscribing");
        request.subscribe(s -> log.debug("Using " + s));
        request.subscribe(s -> log.debug("Using " + s));
        sleep(2000);
        log.debug("A late subscriber");
        request.subscribe(s -> log.debug("Using " + s));

//        request.connect();
    }

    private static String httpCallObs() {
        log.debug("Sent HTTP Request. Waiting for response");
        sleep(1000);
        log.debug("Got Response.");
        return "response";
    }
}
