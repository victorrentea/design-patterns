package victor.training.oo.behavioral.observable;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;

@Slf4j
public class CombineLatest {


    public static void main(String[] args) {
        Observable<Long> o1 = IrregularEmmit.emitAtIntervals(100, 100, 100, 100, 100);
        Observable<Long> o2 = IrregularEmmit.emitAtIntervals(200, 200, 200, 200, 200);


//        o1.zipWith(o2, (t1,t2)->t1+t2)
        Observable.combineLatest(o1, o2, (t1,t2)->t1+t2)
                .toBlocking()
                .subscribe(v -> log.debug("poc " + v));


    }
}
