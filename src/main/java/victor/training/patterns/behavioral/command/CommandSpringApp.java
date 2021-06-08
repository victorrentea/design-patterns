package victor.training.patterns.behavioral.command;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.completedFuture;
import static victor.training.patterns.stuff.ThreadUtils.sleepq;

@EnableAsync
@SpringBootApplication
@EnableBinding({Sink.class, Source.class})
public class CommandSpringApp {
   public static void main(String[] args) {

      SpringApplication.run(CommandSpringApp.class, args); // Note: .close to stop executors after CLRunner finishes
      sleepq(4000);
   }

   @Bean
   public ThreadPoolTaskExecutor beerPool(@Value("${beer.count}") int poolSize) {
      ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
      executor.setCorePoolSize(poolSize);
      executor.setMaxPoolSize(poolSize);
      executor.setQueueCapacity(500);
      executor.setThreadNamePrefix("beer-");
      executor.initialize();
//      executor.setTaskDecorator(new TaskDecorator() {
//         @Override
//         public Runnable decorate(Runnable runnable) {
//            curretUser =  ThreadLocal.get() // from the caller thread
//            return () -> {
//               ThreadLocal.set(currentUser) // restoring user metadata on thread local in the worker thread.
//               runnable.run();
//            };
//         }
//      });
//      executor.setRejectedExecutionHandler(new AbortPolicy());
//      executor.setRejectedExecutionHandler(new CallerRunsPolicy());
      executor.setWaitForTasksToCompleteOnShutdown(true);
      return executor;
   }

   @Bean
   public ThreadPoolTaskExecutor vodkaPool(@Value("${vodka.count}") int poolSize) {
      ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
      executor.setCorePoolSize(poolSize);
      executor.setMaxPoolSize(poolSize);
      executor.setQueueCapacity(500);
      executor.setThreadNamePrefix("vodka-");

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
//   @Autowired
//   private ThreadPoolTaskExecutor pool;

   // TODO [1] inject and use a ThreadPoolTaskExecutor.submit
   // TODO [2] make them return a CompletableFuture + @Async + asyncExecutor bean
   // TODO [3] wanna try it out over JMS? try out ServiceActivatorPattern
//   public void run(String... args) throws ExecutionException, InterruptedException {
   @GetMapping("drink")
   public CompletableFuture<DillyDilly> method() { // async servlet

      // FOR GET "retrieving data" NOT FOR UPDATE/INSERT


//      HttpServletRequest request;
//      request.startAsync()
      log.debug("Submitting my order +" + barman.getClass());
      long t0 = System.currentTimeMillis();
      log.debug("Waiting for my drinks...");

//      CompletableFuture<Void> voidCompletableFuture = CompletableFuture.runAsync(payenet);

      CompletableFuture<Beer> futureBeer = barman.pourBeer(); // ~ promise in FE
      CompletableFuture<Vodka> futureVodka = barman.pourVodka();


      CompletableFuture<DillyDilly> futureDilly = futureBeer.thenCombine(futureVodka, (b, v) -> new DillyDilly(b, v));


      futureDilly.thenAccept(dilly -> log.debug("Got my order ms ! Enjoying {}", dilly));
      log.info("Thread is free");
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
   @Async("beerPool")
   public CompletableFuture<Beer> pourBeer() {
      log.debug("Pouring Beer...");
//      RestTemplate.getForObject >> non blocking REST calls : WebClient (Spring 5 + Reactor) Mono
      sleepq(1000); // Please imagine here a BLOCKING REST call
      return completedFuture(new Beer());
   }

   @Async("vodkaPool")
   public CompletableFuture<Vodka> pourVodka() {
      log.debug("Pouring Vodka...");
      sleepq(1000); // another blockin REST call / DB call = external network calls
      return completedFuture(new Vodka());
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
