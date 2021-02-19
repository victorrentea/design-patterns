package victor.training.patterns.behavioral.observer;

import lombok.extern.slf4j.Slf4j;
import rx.Observable;
import rx.schedulers.Schedulers;

import java.util.Arrays;

@Slf4j
public class ReactiveXSwitchThread {

   public static void main(String[] args) throws InterruptedException {

      Observable.from(Arrays.asList(1))
          .subscribeOn(Schedulers.io())
          .map(i -> {
             log.info("IO " + i);
             return i * i;
          })
          .observeOn(Schedulers.computation())
          .map(i -> {
             log.info("CPU " + i);
             return i * i;
          })
          .observeOn(Schedulers.io())
          .subscribe(e -> {
             log.info("got " + e);
          });
      Thread.sleep(4000);
   }
}
