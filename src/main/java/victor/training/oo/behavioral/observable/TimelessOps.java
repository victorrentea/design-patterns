package victor.training.oo.behavioral.observable;

import rx.Observable;
import victor.training.oo.stuff.ThreadUtils;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class TimelessOps {
    public static void main(String[] args) {


        Observable<Integer> numbers = Observable.from(Arrays.asList(1, 2, 3, 3, 4, 5,7, 7,2,4,8,2,10,5));
        numbers.distinct()
                .doOnNext(e -> System.out.println("BUM"))
                .contains(7) // face un fel de break; ~ short-circuiting
                .subscribe(System.out::println);


        Random r = new Random();

        Observable.interval(500, TimeUnit.MILLISECONDS)
                .take(6)
                .map(tick -> r.nextInt(100))
                .doOnNext(n -> System.out.println("Acum: " +n))
//                .scan(0, Integer::max)
                .reduce(0, Integer::max)
                .subscribe(max -> System.out.println("Max: " + max));

        ThreadUtils.sleep(5000);
    }
}
