package victor.training.oo.behavioral.command;

import lombok.Data;
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
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static java.util.Arrays.asList;
import static victor.training.oo.stuff.ThreadUtils.sleepq;

@EnableAsync
@SpringBootApplication
@EnableBinding({Sink.class, Source.class})
public class CommandSpringApp {
   public static void main(String[] args) {
      SpringApplication.run(CommandSpringApp.class, args).close(); // Note: .close to stop executors after CLRunner finishes
   }

   @Bean
   public ThreadPoolTaskExecutor executor(@Value("${barman.count}")int barmanCount) {
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
class Beutor implements CommandLineRunner {
   @Autowired
   private Barman barman;
   @Autowired
   private ServiceActivatorPattern serviceActivatorPattern;

   @Autowired
   private ThreadPoolTaskExecutor pool;

   // TODO [1] inject and use a ThreadPoolTaskExecutor.submit
   // TODO [2] make them return a CompletableFuture + @Async + asyncExecutor bean
   // TODO [3] wanna try it out over JMS? try out ServiceActivatorPattern
   public void run(String... args) throws ExecutionException, InterruptedException {
      log.debug("Submitting my order: " + barman.getClass());
      long t0 = System.currentTimeMillis();
      log.debug("Waiting for my drinks...");

      Future<Beer> beerFuture = barman.pourBeer();
      Future<Vodka> vodkaFuture = barman.pourVodka();

      Beer beer = beerFuture.get(); // blocks main thread until beed is poured
      Vodka vodka = vodkaFuture.get();

      long t1 = System.currentTimeMillis();
      log.debug("Got my order in {} ms ! Enjoying {}", t1 - t0, asList(beer, vodka));

      barman.injura("*@!*&*&*#&");
      log.info("Ma bag in patutzul caldutz");
   }
}

@Slf4j
@Service
class Barman {
   @Async
   public Future<Beer> pourBeer() {
//      if (true) {
//         throw new IllegalStateException("Nu mai e bere!");
//      }
      log.debug("Pouring Beer...");
      sleepq(1000); // apel de WEbService peste REST
      return CompletableFuture.completedFuture(new Beer());
   }

   @Async
   public Future<Vodka> pourVodka() {
      log.debug("Pouring Vodka...");
      sleepq(1000); // DB Query
      return CompletableFuture.completedFuture(new Vodka());
   }

   @Async
   public void injura(String uratura) {
      throw new IllegalArgumentException(uratura + uratura);
   }
}

@Data
class Beer {
}

@Data
class Vodka {
}
