package victor.training.oo.behavioral.observable;

import rx.Observable;
import rx.schedulers.Schedulers;
import victor.training.oo.structural.adapter.domain.User;
import victor.training.oo.stuff.ConcurrencyUtil;

import java.util.Arrays;

import static rx.schedulers.Schedulers.computation;
import static rx.schedulers.Schedulers.io;
import static victor.training.oo.stuff.ConcurrencyUtil.*;

public class ThreadControl {

    public static void main(String[] args) {
        Observable.defer(() -> getUsers())
                .map(s-> {
                    log("map");
                    return s.toUpperCase();
                })
//                .subscribeOn(computation())
                .observeOn(computation())
                .doOnNext(s-> log("map2 " +s))
                .observeOn(io())
                .subscribe(s -> log(s));
        log("Done");
        sleep2(100);
    }

    static Observable<String> getUsers() {
        log("Create");
        return Observable.from(Arrays.asList("a","b","c"));
    }
}
