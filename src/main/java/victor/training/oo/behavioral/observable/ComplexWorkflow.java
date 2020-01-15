package victor.training.oo.behavioral.observable;

import javafx.util.Pair;
import rx.Completable;
import rx.Single;
import rx.subjects.PublishSubject;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ComplexWorkflow {
    //    Faci req A (Single); Dupa A, pornesti req B si C si deschizi un dialog;
    //    dupa toate 3, daca la dialog=OK, faci un task cu calcule B,
    //    apoi faci un rez D de tip post (Completable) si dupa ce e gata pui un
    //    toaster pe MainThread.


    public static void main(String[] args) {

        Single<String> aObs = httpRequestA();

        Single<String> bObs = aObs.flatMap(s -> httpRequestB(s));
        Single<String> cObs = aObs.flatMap(s -> httpRequestC(s));
        Single<Boolean> dObs = aObs.flatMap(s -> dialog(s));

//        dObs
        Single<String> bcObc = bObs.zipWith(cObs, String::concat);

        dObs.toObservable()
                .zipWith(bcObc.toObservable(), Pair::new)
                .filter(Pair::getKey)
                .map(Pair::getValue)
                .flatMap(x ->
                        httpRequestPostD(x).toObservable()
                            .doOnCompleted(() -> System.out.println("Toast OK"))
                )
                .subscribe(x -> System.out.println("XX:" +x))
        ;

        ug();
    }

    private static Single<Boolean> dialog(String s) {
        System.out.println("Deschid dialog " + s);
        dialogResult.doOnNext(b -> System.out.println("Dialog: " + b));
        return dialogResult.toSingle();
    }

    static PublishSubject<Boolean> dialogResult = PublishSubject.create();
    private static void ug() {
        Scanner scanner = new Scanner(System.in);
        String line;
        while ((line = scanner.nextLine()) != null) {
            System.out.println("IN " + line);
            switch (line) {
                case "ok":
                    System.out.println("ooooo");
                    dialogResult.onNext(true);
                    dialogResult.onCompleted();
                    break;
                case "cancel": dialogResult.onNext(false);
                    dialogResult.onCompleted();
                break;
            }
        }
    }

    static Single<String> httpRequestA() {
        System.out.println("Sending a");
        return Single.just("a").delay(1, TimeUnit.SECONDS).doOnSuccess(System.out::println);
    }
    static Single<String> httpRequestB(String s) {
        System.out.println("Sending b");
        return Single.just(s.toUpperCase()).delay(1, TimeUnit.SECONDS).doOnSuccess(System.out::println);
    }
    static Single<String> httpRequestC(String s) {
        System.out.println("Sending c");
        return Single.just(s+s).delay(1, TimeUnit.SECONDS).doOnSuccess(System.out::println);
    }
    static Completable httpRequestPostD(String bc) {
        System.out.println("Sending d");
        System.out.println("Sending " + bc);
        return Completable.timer(1, TimeUnit.SECONDS).doOnCompleted(System.out::println);
    }
}
