package victor.training.patterns.facade.controller;

import victor.training.patterns.facade.facade.dto.CustomerDto;

import java.util.concurrent.Callable;

import static java.lang.System.currentTimeMillis;

public class Util {
    public static <T> T measure(Callable<T> work) throws Exception {
        long t0 = currentTimeMillis();
        T r = work.call();
        long t1 = currentTimeMillis();
        long delta = t1 - t0;
        System.out.println("Took " +delta);
        return r;
    } // ghena

}
