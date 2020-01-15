package victor.training.oo.behavioral.observable.pitfall;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.observables.ConnectableObservable;
import rx.subjects.BehaviorSubject;

import java.util.concurrent.TimeUnit;

import static victor.training.oo.stuff.ThreadUtils.sleep;

@Slf4j
public class SharingRequests {
    public static void main(String[] args) {
        log.debug("Start");

        Observable<String> call = httpCallObs();
        ConnectableObservable<String> birouDeschis = call.publish();
        // TODO avoid doing two expensive network calls
        birouDeschis.subscribe(v -> System.out.println("Pun in cas: " + v));
        // aici mai 1 ns
        birouDeschis.subscribe(v-> System.out.println("Dau aplicatiei"));
        birouDeschis.connect(); // abia acum observable-ul incepe sa emita
        // .connect() poate fi enervant.
        birouDeschis.subscribe(v-> System.out.println("Dau aplicatiei 3 = NU RULEAZA"));


        System.out.println("===========2==================");

        Observable<String> pornesteAutomatCandAre2 = httpCallObs().publish().autoConnect(2);
        // TODO avoid doing two expensive network calls
        pornesteAutomatCandAre2.subscribe(v -> System.out.println("Pun in cas: " + v));
        // aici mai 1 ns
        pornesteAutomatCandAre2.subscribe(v -> System.out.println("Dau aplicatiei"));
        pornesteAutomatCandAre2.subscribe(v -> System.out.println("Dau aplicatiei 3  = NU RULEAZA"));




        System.out.println("===========3==================");


        getHttpSauCache().subscribe(System.out::println);

        System.out.println("===========4==================");


        getHttpSauCache();


        System.out.println("===========timp==================");
        Observable<Long> timp = Observable.interval(100, TimeUnit.MILLISECONDS).share();
        timp.subscribe(System.out::println);
        sleep(1000);
        timp.subscribe(System.out::println);
        sleep(2000);
    }

    private static Observable<String> getHttpSauCache() {
        // PROBLEMA: iti face HTTP req chiar daca observableul intors nu e subscris de nimeni.
        BehaviorSubject<String> subject = BehaviorSubject.create();
        httpCallObs()
                .subscribe(v -> {
                    System.out.println("punInCache " + v);
                    subject.onNext(v);
                });
        return subject.asObservable();

        // parca tot mai bun e asta:
//        return httpCallObs().doOnNext(v -> System.out.println("punInCache " + v));

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
