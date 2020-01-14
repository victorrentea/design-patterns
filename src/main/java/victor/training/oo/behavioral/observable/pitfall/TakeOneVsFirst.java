package victor.training.oo.behavioral.observable.pitfall;

import rx.Observable;

public class TakeOneVsFirst {
    public static void main(String[] args) {
        // What's the difference ?!
        System.out.println("take(1):");
        obs().take(1).subscribe(System.out::println);
        System.out.println("first():");
        obs().first().subscribe(System.out::println); // often, the best
    }

    private static Observable<String> obs() {
        // TODO what if .... ?
        return Observable.just("a");
    }
}
