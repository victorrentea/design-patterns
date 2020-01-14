package victor.training.oo.behavioral.observable.pitfall;

import rx.Observable;

public class CreatingObservables {

    static String s;


    public static Observable<String> gimmeString() {
        // TODO just from create fromCallable deferred
        return null;
    }

    public static void main(String[] args) {
        gimmeString().subscribe(System.out::println);
    }
}
