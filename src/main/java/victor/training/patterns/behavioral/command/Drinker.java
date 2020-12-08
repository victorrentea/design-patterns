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

import java.util.concurrent.*;

import static java.util.Arrays.asList;
import static victor.training.patterns.stuff.ThreadUtils.sleepq;


@Slf4j
public class Drinker  {

   private static Barman barman = new Barman();

   public static void main(String[] args) throws ExecutionException, InterruptedException {
      log.debug("Submitting my order");
      long t0 = System.currentTimeMillis();
      log.debug("Waiting for my drinks...");

      ExecutorService pool = Executors.newFixedThreadPool(2);

      Future<Beer> futureBeer = pool.submit(() -> barman.pourBeer());
      Future<Vodka> futureVodka = pool.submit(() -> barman.pourVodka());

      log.debug("Aici a plecat chelnerul cu comanda");

      Beer beer = futureBeer.get(); // cat timp se sta aici: 1s
      Vodka vodka = futureVodka.get();// cat timp se sta aici? 0s pentru ca acea secunda pt vodka s-a scurs intre timp deja

      long t1 = System.currentTimeMillis();
      log.debug("Got my order in {} ms ! Enjoying {}", t1 - t0, asList(beer, vodka));
   }
}

@Slf4j
class Barman {
   public Beer pourBeer() {
      log.debug("Pouring Beer...");
      sleepq(1000);
      return new Beer();
   }

   public Vodka pourVodka() {
      log.debug("Pouring Vodka...");
      sleepq(1000);
      return new Vodka();
   }
}

@Data
class Beer {
}

@Data
class Vodka {
}
