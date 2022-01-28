package victor.training.patterns.behavioral.command;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.Arrays.asList;
import static victor.training.patterns.stuff.ThreadUtils.sleepq;

@EnableAsync
@SpringBootApplication
@EnableBinding({Sink.class, Source.class})
public class CommandSpringApp {
   public static void main(String[] args) {
      SpringApplication.run(CommandSpringApp.class, args).close(); // Note: .close to stop executors after CLRunner finishes
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

      ExecutorService threadPool = Executors.newFixedThreadPool(2);

//      Beer beer = barman.pourBeer();
//      Vodka vodka = barman.pourVodka();

      CompletableFuture<Beer> futureBeer = CompletableFuture.supplyAsync(() -> barman.pourBeer())
//          .exceptionally(e -> dummyBeer)
          ;
      CompletableFuture<Vodka> futureVodka = CompletableFuture.supplyAsync(() -> barman.pourVodka());

//      Beer beer = futureBeer.get();
//      Vodka vodka = futureVodka.get();

      CompletableFuture<String> feelingFuture = futureBeer.thenCombineAsync(futureVodka, (beer, vodka) -> {
         long t1 = System.currentTimeMillis();
         log.debug("Got my order in {} ms ! Enjoying {}", t1 - t0, asList(beer, vodka));
         return "happy";
      });


      sleepq(5000); // keep the app running a bit
   }
}

@Slf4j
@Service
class Barman {
   public Beer pourBeer() {
//      if (true) throw new IllegalArgumentException("Out of beer");
      log.debug("Pouring Beer...");
      sleepq(1000); // HTTP
      return new Beer();
   }

   public Vodka pourVodka() {
      log.debug("Pouring Vodka...");
      sleepq(1000); // LARGE OLD
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
