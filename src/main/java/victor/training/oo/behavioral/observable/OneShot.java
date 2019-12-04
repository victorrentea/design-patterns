package victor.training.oo.behavioral.observable;

import lombok.extern.slf4j.Slf4j;
import rx.*;
import rx.schedulers.Schedulers;
import victor.training.oo.stuff.ConcurrencyUtil;

import java.util.Arrays;

@Slf4j
public class OneShot {
    public static void main(String[] args) {
        Single<String> just = Observable.just("One-shot").toSingle();

        Subscriber s;
        Observer o;
        Observable.from(Arrays.asList(1,2,3,4,5,6,7,8,9))
                .subscribeOn(Schedulers.computation())
                .map(n -> n * n)
                .subscribe(new Observer<Integer>(){

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Integer integer) {

                    }
                });
//                .subscribe(n -> log.debug("Out: " +n));

        ConcurrencyUtil.sleep2(1000);
    }
}
