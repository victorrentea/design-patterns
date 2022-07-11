package victor.training.patterns.command;

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

import java.util.concurrent.*;

import static java.util.Arrays.asList;
import static victor.training.patterns.util.ThreadUtils.sleepq;

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

   private static final ExecutorService threadPool = Executors.newFixedThreadPool(5);
   // altfel,


   // TODO [1] inject and use a ThreadPoolTaskExecutor.submit
   // TODO [2] make them return a CompletableFuture + @Async + asyncExecutor bean
   // TODO [3] wanna try it out over JMS? try out ServiceActivatorPattern
   public void run(String... args) throws ExecutionException, InterruptedException {
      log.debug("Submitting my order");
      long t0 = System.currentTimeMillis();
      log.debug("Waiting for my drinks...");

      // alternative:
      // 1) CompletableFuture.supplyAsync().thenAccept(result->...)
      // 2) @Async in Spring
      // 2) dar mai safe) ThreadPoolTaskExecutor - cel mai rasp mod de a face async chestii in Spring
      // 3) Reactive Programming (Mono.supplyAsync( -> ))

//      Beer beer = barman.pourBeer(); //apel  inlocuit cu obiect/mesaj dat cuiva
      Callable<Beer> comandaDeBere = () -> barman.pourBeer();
      Future<Beer> futureBeer = threadPool.submit(comandaDeBere);

      Vodka vodka = barman.pourVodka();
      Beer beer = futureBeer.get();
      long t1 = System.currentTimeMillis();
      log.debug("Got my order in {} ms ! Enjoying {}", t1 - t0, asList(beer, vodka));
   }
}

@Slf4j
@Service
class Barman {
   public Beer pourBeer() {
      log.debug("Pouring Beer (3 second)...");
      sleepq(3000);// RestTemopklat ,.,.., chemn
      // - tine atat de mult incat ai prefera sa faci alte chestii in timp ce astepti. (alte treburi din acelasi flux)
      // - un serviciu extern Beer Legacy System care poate procesa doar 2 comenzi simultane.
      // - te costa mai mult daca trimiti req in parallel
      // - sistem legacy pe care daca lansezi > 5 req in paralel, si misca de 3x mai lent.
      return new Beer();
   }

   public Vodka pourVodka() {
      log.debug("Pouring Vodka (1 second)...");
      sleepq(1000);
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
