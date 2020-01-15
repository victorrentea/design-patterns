package victor.training.oo.behavioral.observable.bug;

import rx.Observable;

public class RetryAndResubscribe {
    static int i;
    public static void main(String[] args) {

        Observable.fromCallable(() -> {
            if (i++ < 3) {
                throw new IllegalArgumentException();
            } else {
                return "a";
            }
        })
                .doOnSubscribe(()-> System.out.println("new subscr"))
                .doOnEach(System.out::println)
                .retry(5)
                .subscribe(System.out::println);

    }
}
