package victor.training.oo.behavioral.observable.pitfall;

import rx.Observable;
import rx.schedulers.Schedulers;
import victor.training.oo.stuff.ThreadUtils;

public class OpaqueExTraces {
    public static void main(String[] args) {

        // TODO find this code in the stacktrace; Make it appear
        Observable.empty()
                .first()
                .subscribeOn(Schedulers.io())
                .subscribe(System.out::println);

        ThreadUtils.sleep(100);
    }
}
