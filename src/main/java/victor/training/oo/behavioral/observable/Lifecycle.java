package victor.training.oo.behavioral.observable;

import rx.Observable;

import java.util.concurrent.TimeUnit;

public class Lifecycle {
    public static void main(String[] args) {
        dauLaAltu()
                .doOnNext(tick -> System.out.println(tick + " Fac #sieu aici niste logica1, ca sa o imprastii bine. O parte in subscribe si alta aici."))
                .doOnNext(tick -> System.out.println(tick + " Fac #sieu aici niste logica2, ca sa o imprastii bine. O parte in subscribe si alta aici."))
                .toBlocking()
                .subscribe();

        dauLaAltu()
            .subscribe();

        Observable.just(1, 2, 3)
                .doOnRequest(d -> System.out.println("Cer " + d))
                .first()
                .subscribe(System.out::println);
    }
    // GARD ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
    // |||||||||||||||||||||||||||||||
    // sub, e codul **MEU**

    private static Observable<Long> dauLaAltu() {
        return Observable.interval(100, TimeUnit.MILLISECONDS)
                // USAGE: cand vrei sa faci niste logging
                .doOnNext(tick -> System.out.println("Loggez " + tick))
                // USAGE: tot pt logging ca sa vezi daca se repeta op scumpe lansate de subscribe
                // Inutil pt Observableurile **HOT** pt ca la alea nu le pasa ca faci subscribe (nimic nu se intampla cand te abonezi)
                .doOnSubscribe(() -> System.out.println("Inca un client... care cere un HTTP request de 500 ms/buc sau alta operatie scumpa"))
                // Daca uiti sa te desubscrii la Observableuri -> poti da-n leakuri
                .doOnUnsubscribe(() -> System.out.println("Memory leak chance --"))
                // sa nu fie un observable nesimtzit, .never()
                .doOnCompleted(() -> System.out.println("Mi-a spus 'GATA'"))
                .doOnRequest(d -> System.out.println("Cer "+ d))
                .take(3);
    }
}
