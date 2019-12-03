package victor.training.oo.behavioral.observable;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import victor.training.oo.stuff.ConcurrencyUtil;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class Transformations {
    public static void main(String[] args) {
        sendPointsToGame(Arrays.asList(1, 3, 2, 5, 4, 1, 2, 5, 6));
        sendWonPoint(Arrays.asList("A", "B", "B", "A", "B", "B", "A", "A", "A"));
    }

    private static void sendWonPoint(List<String> wonSequence) {
        int[] points = new int[2];
        Observable.from(wonSequence)
                .groupBy(p -> "A".equals(p) ? 1 : 0)
                .subscribe(group -> {
                    log.debug("Emit Group " + group);
                    Integer playerIndex = group.getKey();
                    group.subscribe(p -> points[playerIndex]++);
                });
        System.out.println(Arrays.toString(points));
    }

    private static void sendPointsToGame(List<Integer> pointsSequence) {
        Observable<Integer> pointsObservable =
                Observable.from(pointsSequence)
                        .map(n -> {
                            ConcurrencyUtil.sleep2(100);
                            return n;
                        });

        Observable<Integer> totalPointsObservable = pointsObservable.scan(0, Integer::sum);

        totalPointsObservable.sample(200, TimeUnit.MILLISECONDS)
                .subscribe(n -> System.out.println("Points " + n));
    }
}
