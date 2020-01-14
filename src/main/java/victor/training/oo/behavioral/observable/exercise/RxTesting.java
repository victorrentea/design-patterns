package victor.training.oo.behavioral.observable.exercise;

import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.Schedulers;
import rx.schedulers.TestScheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

public class RxTesting
{
    @Test
    public void synchronousObservers() {

        Observable<Integer> indexesObs = Observable.range(1, Integer.MAX_VALUE);
        Observable<String> stringsObs = Observable.from("abc".split(""))
                .zipWith(indexesObs, (s, i) -> i + ":" + s);

        // TODO
        List<String> results = new ArrayList<>();
        stringsObs.subscribe(results::add);

        assertEquals(asList("1:a", "2:b", "3:c"), results);
    }

    @Test
    public void testSubscriber() {
        Observable<Integer> indexesObs = Observable.range(1, Integer.MAX_VALUE);
        Observable<String> stringsObs = Observable.from("abc".split(""))
                .zipWith(indexesObs, (s, i) -> i + ":" + s);

        // TODO TestSubscriber
        TestSubscriber<String> subscriber = TestSubscriber.create();
        stringsObs.subscribe(subscriber);
        subscriber.assertCompleted();
        subscriber.assertValues("1:a", "2:b", "3:c");
    }

    @Test
    public void testSubscriberErr() {
        Observable<Integer> indexesObs = Observable.range(1, Integer.MAX_VALUE);
        Observable<String> stringsObs = Observable.from("abc".split(""))
                .zipWith(indexesObs, (s, i) -> i + ":" + s)
                .concatWith(Observable.error(new IllegalStateException("hah")));

        // TODO
        TestSubscriber<String> subscriber = TestSubscriber.create();
        stringsObs.subscribe(subscriber);
        subscriber.assertError(IllegalStateException.class);
        Throwable throwable = subscriber.getOnErrorEvents().get(0);
        assertEquals("hah",throwable.getMessage());
    }

    @Test
    public void manualTimeAdvance() {
        // INITIAL:
//        Observable<Long> indexesObs = Observable.interval(1, TimeUnit.SECONDS);
//        Observable<String> stringsObs = Observable.from("abc".split(""))
//                .zipWith(indexesObs, (s, i) -> i + ":" + s);


        // TODO TestScheduler
        TestScheduler testScheduler = Schedulers.test();
        Observable<Long> indexesObs = Observable.interval(1, TimeUnit.SECONDS, testScheduler);
        testScheduler.advanceTimeBy(1100, TimeUnit.MILLISECONDS);
        Observable<String> stringsObs = Observable.from("abc".split(""))
                .zipWith(indexesObs, (s, i) -> i + ":" + s);

        List<String> results = new ArrayList<>();
        stringsObs.subscribe(results::add);
//        ThreadUtils.sleep(1500);
        testScheduler.advanceTimeBy(1500, TimeUnit.MILLISECONDS);
        assertEquals(asList("0:a"), results);

        testScheduler.advanceTimeBy(2000, TimeUnit.MILLISECONDS);
//        ThreadUtils.sleep(2000);
        assertEquals(asList("0:a", "1:b", "2:c"), results);
    }




}
