package victor.training.oo.behavioral.observable;


import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import rx.Observable;

import java.util.concurrent.TimeUnit;

import static victor.training.oo.stuff.ThreadUtils.sleep;

@Slf4j
public class TimelessOps {
    public static void main(String[] args) {

        log.debug("Start");

        // useru face un click pt filmul 13
        Observable<Long> movieIdObs = Observable.just(13L);


        movieIdObs.flatMap(id ->
                Observable.zip(requestPlot(id), requestRating(id), PlotAndRating::new))
                .subscribe(pr -> display(pr.getPlot(), pr.getRating()));

        sleep(3000);
    }

    public static void display(String plot, float rating) {
        System.out.println("Movie " + plot + " and " + rating);
    }

    public static Observable<Float> requestRating(long movieId) {
        return Observable.just(3.5f)
                    .delay(1, TimeUnit.SECONDS);
    }
    public static Observable<String> requestPlot(long movieId) {
        return Observable.just("mare")
                    .delay(2, TimeUnit.SECONDS);
    }
}


@Value
class PlotAndRating {
    String plot;
    float rating;
}