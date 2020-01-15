package victor.training.oo.behavioral.observable.exercise;

import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;
import rx.schedulers.TestScheduler;

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

        List<String> actual = stringsObs.toList().toBlocking().first();
        assertEquals(asList("1:a","2:b","3:c"), actual);
    }

    @Test
    public void testSubscriber() {
        Observable<Integer> indexesObs = Observable.range(1, Integer.MAX_VALUE);
        Observable<String> stringsObs = Observable.from("abc".split(""))
                .zipWith(indexesObs, (s, i) -> i + ":" + s);

        TestSubscriber<String> testSubscriber = new TestSubscriber<>();

        stringsObs.subscribe(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertCompleted();
        testSubscriber.assertValues("1:a","2:b","3:c");

    }
    @Test
    public void testSubscriberErr() {
        Observable<Integer> indexesObs = Observable.range(1, Integer.MAX_VALUE);
        Observable<String> stringsObs = Observable.from("abc".split(""))
                .zipWith(indexesObs, (s, i) -> i + ":" + s)
                .concatWith(Observable.error(new IllegalStateException("hah")));

        TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        stringsObs.subscribe(testSubscriber);

        testSubscriber.assertError(IllegalStateException.class);
        Throwable exceptie = testSubscriber.getOnErrorEvents().get(0);
        assertEquals("hah", exceptie.getMessage());

    }

    @Test
    public void manualTimeAdvance() {
        TestScheduler testScheduler = new TestScheduler();
        Observable<Long> indexesObs = Observable.interval(1, TimeUnit.SECONDS,testScheduler);
        Observable<String> stringsObs = Observable.from("abc".split(""))
                .zipWith(indexesObs, (s, i) -> i + ":" + s);

        stringsObs = stringsObs.doOnEach(notif -> System.out.println(notif));
        TestSubscriber<String> testSubscriber = new TestSubscriber<>();
        stringsObs.subscribe(testSubscriber);
        // dupa 1 sec
        testSubscriber.assertReceivedOnNext(asList());
        testScheduler.advanceTimeBy(1001, TimeUnit.MILLISECONDS);
        testSubscriber.assertReceivedOnNext(asList("0:a"));
        testScheduler.advanceTimeBy(2, TimeUnit.SECONDS);
        testSubscriber.assertReceivedOnNext(asList("0:a","1:b","2:c"));


    }




}
