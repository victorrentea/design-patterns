package victor.training.patterns.command;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.*;

import static victor.training.patterns.stuff.ThreadUtils.sleepq;

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
      executor.setCorePoolSize(2);
      executor.setMaxPoolSize(2);
      executor.setQueueCapacity(500);
      executor.setThreadNamePrefix("barman-");
      executor.initialize();
      executor.setWaitForTasksToCompleteOnShutdown(true);
      return executor;
   }

}

@Slf4j
@RestController
class Drinker {
   @Autowired
   private Barman barman;
   @Autowired
   private ServiceActivatorPattern serviceActivatorPattern;
   @Autowired
   private ThreadPoolTaskExecutor threadPool;

   // TODO [1] inject and use a ThreadPoolTaskExecutor.submit
   // TODO [2] make them return a CompletableFuture + @Async + asyncExecutor bean
   // TODO [3] wanna try it out over JMS? try out ServiceActivatorPattern
   @GetMapping("drink")
   public CompletableFuture<DillyDilly> drink() throws ExecutionException, InterruptedException {
      log.debug("Submitting my order cui: " + barman.getClass());

      long t0 = System.currentTimeMillis();
      log.debug("Waiting for my drinks...");

      CompletableFuture<Beer> futureBeer = barman.pourBeer();
      CompletableFuture<Vodka> futureVodka = barman.pourVodka();

//      Beer beer = futureBeer.get(); // 1 secunda sta main blocat aici
//      Vodka vodka = futureVodka.get(); // ~0 secunde sta main blocat aici

      CompletableFuture<DillyDilly> futureDilly = futureBeer.thenCombine(futureVodka, (beer, vodka) -> new DillyDilly(beer, vodka));

      long t1 = System.currentTimeMillis();
      log.debug("Got my order in {} ms ! Enjoying ", t1 - t0);
      return futureDilly;

   }
}

@Data
class DillyDilly {
   private final Beer beer;
   private final Vodka vodka;
}

@Slf4j
@Service
class Barman {
   @Async//("threadPoolulMeu")
   public CompletableFuture<Beer> pourBeer() {
      log.debug("Pouring Beer (1 second) REST CALL ...");
      sleepq(1000);
      return CompletableFuture.completedFuture(new Beer());
   }

   @Async
   public CompletableFuture<Vodka> pourVodka() {
      log.debug("Pouring Vodka (1 second) SOAP/RMI/DB...");
      sleepq(1000);
      return CompletableFuture.completedFuture(new Vodka());
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
