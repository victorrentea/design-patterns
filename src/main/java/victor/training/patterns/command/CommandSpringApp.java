package victor.training.patterns.command;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.framework.qual.CFComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

import static java.util.Arrays.asList;
import static java.util.concurrent.CompletableFuture.supplyAsync;
import static victor.training.patterns.util.ThreadUtils.sleepq;

@EnableAsync
@SpringBootApplication
@EnableBinding({Sink.class, Source.class})
public class CommandSpringApp {
    public static void main(String[] args) {
        SpringApplication.run(CommandSpringApp.class, args); // Note: .close to stop executors after CLRunner finishes
    }

    @Bean
    public ThreadPoolTaskExecutor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(1);
        executor.setMaxPoolSize(1);
        executor.setQueueCapacity(500);
        executor.setThreadNamePrefix("barman-");
        executor.initialize();
        executor.setWaitForTasksToCompleteOnShutdown(true);
        return executor;
    }

}

@Slf4j
@Component
class Drinker implements CommandLineRunner {
    @Autowired
    private Barman barman;
    @Autowired
    private ServiceActivatorPattern serviceActivatorPattern;

    // TODO [1] inject and use a ThreadPoolTaskExecutor.submit
    // TODO [2] make them return a CompletableFuture + @Async + asyncExecutor bean
    // TODO [3] wanna try it out over JMS? try out ServiceActivatorPattern
    public void run(String... args) throws ExecutionException, InterruptedException {
        log.debug("Submitting my order");
        long t0 = System.currentTimeMillis();
        log.debug("Waiting for my drinks...");
        //Executors/ ThreadPoolTaskExecutor(Spring) /COmpletableFuture / @Async(Spring)

        // command  an object not a call.
        // DO NOT do I/O (network calls) in the ForkJoinPool.commonPool in JVM : in there you should not block
        CompletableFuture<Beer> futureBeer = supplyAsync(() -> barman.pourBeer()/*, myExecutor*/);
        CompletableFuture<Vodka> futureVodka = supplyAsync(() -> barman.pourVodka());

        //      Beer beer = futureBeer.get(); // main thread waits here 1 sec => BAD
        //      Vodka vodka = futureVodka.get();

        futureBeer.thenAcceptBoth(futureVodka, (beer, vodka) ->
                log.debug("Got my order in  Enjoying {}", asList(beer, vodka))
        );
        long t1 = System.currentTimeMillis();
        System.out.println("main thread is free after: " + (t1-t0));
    }
}

@Slf4j
@Service
class Barman {
    public Beer pourBeer() {
        log.debug("Pouring Beer (1 second)...");
        sleepq(1000); // long REST/WSDL Call
        return new Beer();
    }

    public Vodka pourVodka() {
        log.debug("Pouring Vodka (1 second)...");
        sleepq(1000); // long query / another api call
        return new Vodka();
    }
}

@Data
class Beer {
    private final String type = "BLOND";
}

@Data
class Vodka {
    private final String make = "STALINSKAYA";
}
