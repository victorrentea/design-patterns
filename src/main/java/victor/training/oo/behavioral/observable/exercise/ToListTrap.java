package victor.training.oo.behavioral.observable.exercise;

import rx.subjects.PublishSubject;

public class ToListTrap {
    public static void main(String[] args) {

        PublishSubject<String> p = PublishSubject.create();
        p.onNext("a");
        p.onCompleted();

        p.toList().subscribe(System.out::println);
    }
}
