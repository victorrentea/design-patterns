package victor.training.oo.behavioral.observable;

import rx.Observable;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;
import victor.training.oo.stuff.ThreadUtils;

import java.util.concurrent.TimeUnit;

public class Subscriptie {

    public static void main(String[] args) {
        CompositeSubscription compositeSubscription = new CompositeSubscription();

        Observable<Long> o1 = Observable.interval(100, TimeUnit.MILLISECONDS);

        Observable<Long> o2 = Observable.interval(200, TimeUnit.MILLISECONDS);

        compositeSubscription.add(o1.subscribe(System.out::println));
        compositeSubscription.add(o2.subscribe(System.out::println));

        ThreadUtils.sleep(1000);
        // ies din pagina
        // din callbackup de iesire
        compositeSubscription.clear();
        //vs .unsubscribe
        System.out.println("Am iesit");
        ThreadUtils.sleep(1000);
        System.out.println("Am intrat din nou");


        compositeSubscription.add(o1.subscribe(System.out::println));
        compositeSubscription.add(o2.subscribe(System.out::println));
        ThreadUtils.sleep(1000);

        compositeSubscription.clear();
        System.out.println("Am iesit ----");


        Subscriber<Long> s = new Subscriber<Long>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable e) {
            }
            @Override
            public void onNext(Long aLong) {
                System.out.println(aLong);
            }
        };
        o1.subscribe(s);
        o2.subscribe(s);

        s.unsubscribe();


        ThreadUtils.sleep(1000);

    }
}
