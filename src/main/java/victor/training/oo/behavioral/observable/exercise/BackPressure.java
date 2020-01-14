package victor.training.oo.behavioral.observable.exercise;

import rx.Emitter;
import rx.Observable;
import rx.schedulers.Schedulers;
import victor.training.oo.stuff.ThreadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

import static victor.training.oo.stuff.ConcurrencyUtil.log;
import static victor.training.oo.stuff.ConcurrencyUtil.sleep2;

public class BackPressure {

    static Consumer<Long> task;

    public static void main(String[] args) {


        new Thread(() -> {
            while(true) {
                long v = System.currentTimeMillis();
                log("Gen " + v);
                ThreadUtils.sleep(50);
                if (task != null) {
                    task.accept(v);
                }
            }
        }).start();


        Observable<Long> observable = Observable.create(integerEmitter -> {
            task = aLong -> integerEmitter.onNext(aLong);
        }, Emitter.BackpressureMode.ERROR);

        // TODO subscribe to this observer from another thread and consume items slower
        ExecutorService pool = Executors.newFixedThreadPool(2);
        observable
                .observeOn(Schedulers.from(pool))
                .subscribe(x -> {
                    ThreadUtils.sleep(200);
                    log("Got " + x);
                });
        sleep2(2000);
    }


}
