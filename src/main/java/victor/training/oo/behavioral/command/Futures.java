package victor.training.oo.behavioral.command;

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.runAsync;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static victor.training.oo.stuff.ConcurrencyUtil.log;
import static victor.training.oo.stuff.ConcurrencyUtil.sleep2;

public class Futures {
    public static void main(String[] args) {

        log("Start");
        CompletableFuture<Integer> fire1Future = supplyAsync(Futures::fire1);
        CompletableFuture<Integer> fire2Future = supplyAsync(Futures::fire2);

//        CompletableFuture.allOf(fire1Future, fire2Future).thenRun(() -> {
//            log("After both");
//        });
        fire1Future.thenCombine(fire2Future, Integer::sum).thenAccept(s -> {
            log("Sum: " + s);
        });
        sleep2(3000);
        log("End");
    }

    public static int fire1() {
        sleep2(1000);
        log("Fire1");
        return 1;
    }
    public static int fire2() {
        sleep2(1000);
        log("Fire2");
        return 1;
    }
}
