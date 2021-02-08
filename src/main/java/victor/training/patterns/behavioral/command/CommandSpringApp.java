package victor.training.patterns.behavioral.command;

import lombok.Data;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

import static victor.training.patterns.stuff.ThreadUtils.sleepq;

@EnableAsync
@SpringBootApplication
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
@RestController
class Drinker {
   @Autowired
   private Barman barman;

   @GetMapping
   public CompletableFuture<Dilly> method() {
      log.debug("Submitting my order");
      log.debug("Waiting for my drinks...");
      CompletableFuture<Beer> futureBeer = barman.pourBeer();
      CompletableFuture<Vodka> futureVodka = barman.pourVodka();
      log.debug("The http thread returns to the pool");
      return futureBeer.thenCombineAsync(futureVodka, Dilly::new)
          .thenApply(d -> {
             log.debug("Over here");
             return d;
          });
   }
}

@Value
class Dilly {
   Beer beer;
   Vodka vodka;
}

@Slf4j
@Service
class Barman {
   @Async
   public CompletableFuture<Beer> pourBeer() {
      log.debug("Pouring Beer...");
      sleepq(1000); // REST Calls
      return CompletableFuture.completedFuture(new Beer());
   }

   @Async
   public CompletableFuture<Vodka> pourVodka() {
      log.debug("Pouring Vodka...");
      sleepq(1000);
      return CompletableFuture.completedFuture(new Vodka());
   }
}

@Data
class Beer {
   private final String type = "light";
}

@Data
class Vodka {
   private final String type = "stalinsakay";
}
