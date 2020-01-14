package victor.training.oo.behavioral.observable.pitfall;

import rx.Observable;
import rx.subjects.BehaviorSubject;

public class CreatingObservables {

    private static BehaviorSubject<String> string;

    public static Observable<String> value() {
        // TODO just from create fromCallable deferred
        // TODO #2: BehaviorSubject<String> instead of String. Implement setValue(). Play.
        // TODO #3: BehaviorSubject.complete kills it ->> try this instead: https://github.com/JakeWharton/RxRelay
        return null;
    }


    public static void main(String[] args) {
        value().subscribe(System.out::println);
    }
}
