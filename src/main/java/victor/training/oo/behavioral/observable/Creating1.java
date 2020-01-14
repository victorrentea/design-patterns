package victor.training.oo.behavioral.observable;

import rx.Observable;

public class Creating1 {
    public static void main(String[] args) {
        Observable.just(1) // pt teste
            .repeat(10)
            .subscribe(System.out::println);



    }
}
