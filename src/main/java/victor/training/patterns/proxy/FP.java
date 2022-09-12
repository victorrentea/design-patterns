package victor.training.patterns.proxy;

import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;

import static java.lang.System.currentTimeMillis;

@Slf4j
public class FP {
    public static Boolean log(Supplier<Boolean> supplier) {
        long t0 = currentTimeMillis();
        Boolean reslt = supplier.get();
        long t1 = currentTimeMillis();
        log.debug("Excecution took {} result = {}", t1 - t0, reslt);
        return reslt;
    }
}
