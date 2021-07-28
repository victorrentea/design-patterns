package victor.training.patterns.behavioral.command;

import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import java.util.concurrent.Future;

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
   public ThreadPoolTaskExecutor executor(@Value("${barman.count}") int barmanCount) {
      ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
      executor.setCorePoolSize(barmanCount);
      executor.setMaxPoolSize(barmanCount);
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

   @Autowired
   ThreadPoolTaskExecutor pool;

   // TODO [1] inject and use a ThreadPoolTaskExecutor.submit
   // TODO [2] make them return a CompletableFuture + @Async + asyncExecutor bean
   // TODO [3] wanna try it out over JMS? try out ServiceActivatorPattern
   @SneakyThrows
   public void run(String... args) {
      log.debug("Submitting my order");
      long t0 = System.currentTimeMillis();
      log.debug("Waiting for my drinks...");

      Future<Beer> beerFuture = pool.submit(() -> barman.pourBeer());
      Future<Vodka> vodkaFuture = pool.submit(() -> barman.pourVodka());


      Beer beer = beerFuture.get(); // 1 sec sta aici
      Vodka vodka = vodkaFuture.get(); // main sta 0 secunde aici.

      long t1 = System.currentTimeMillis();
      log.debug("Got my order in {} ms ! Enjoying {}", t1 - t0, asList(beer, vodka));
   }
}

@Slf4j
@Service
class Barman {
   public Beer pourBeer() {
      log.debug("Pouring Beer...");
      sleepq(1000); // REST call
      return new Beer();
   }

   public Vodka pourVodka() {
      log.debug("Pouring Vodka...");
      sleepq(1000); // expensive DB SELECT
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
