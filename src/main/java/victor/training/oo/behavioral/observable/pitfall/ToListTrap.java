package victor.training.oo.behavioral.observable.pitfall;

import rx.subjects.PublishSubject;

public class ToListTrap {
    public static void main(String[] args) {

        PublishSubject<String> p = PublishSubject.create();
        p./*toList().*/subscribe(System.out::println);
        p.onNext("a");
        p.onNext("b");

        p.onCompleted();
    }
}
