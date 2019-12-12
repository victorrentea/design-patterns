package victor.training.oo.behavioral.observable;

import rx.Emitter;
import rx.Observable;
import rx.schedulers.Schedulers;
import victor.training.oo.stuff.ThreadUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

import static victor.training.oo.stuff.ConcurrencyUtil.log;
import static victor.training.oo.stuff.ConcurrencyUtil.sleep2;

public class BackPressure {

    static Observable<Integer> observable;

    public static void main(String[] args) {
        observable = Observable.create(integerEmitter -> {
            f(i -> {
                integerEmitter.onNext(i);
                if (i == 20) {
                    integerEmitter.onCompleted();
                }
            });
        }, Emitter.BackpressureMode.ERROR);

        ExecutorService pool = Executors.newFixedThreadPool(2);
        observable
                .observeOn(Schedulers.from(pool))
            .subscribe(x -> {
            ThreadUtils.sleep(200);
            log("Got " + x);
        });
        sleep2(2000);
    }


    static void f(Consumer<Integer> integerSupplier) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        new Thread(() -> {
            for (int i = 0; i < 20; i++) {
                int v = atomicInteger.incrementAndGet();
                log("Gen " + v);
                ThreadUtils.sleep(50);
                integerSupplier.accept(v);
            }

        }).start();
    }
}
