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

import static java.util.Arrays.asList;
import static java.util.concurrent.CompletableFuture.completedFuture;
import static victor.training.oo.stuff.ThreadUtils.sleep;

@EnableAsync
@SpringBootApplication
@EnableBinding({Sink.class, Source.class})
public class CommandSpringApp {
	public static void main(String[] args) {
		SpringApplication.run(CommandSpringApp.class, args).close(); // Note: .close to stop executors after CLRunner finishes
	}

	@Bean
	public ThreadPoolTaskExecutor executor(@Value("${barman.count:2}") int barmanCount) {
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
	private ThreadPoolTaskExecutor pool;

	// TODO [1] inject and use a ThreadPoolTaskExecutor.submit
	// TODO [2] make them return a CompletableFuture + @Async + asyncExecutor bean
    // TODO [3] wanna try it out over JMS? try out ServiceActivatorPattern
	public void run(String... args) throws ExecutionException, InterruptedException {
		log.debug("Submitting my order");
		CompletableFuture<Beer> futureBeer = barman.pourBeer();
		CompletableFuture<Vodka> futureVodka = barman.pourVodka();

		futureBeer.thenCombine(futureVodka, (beer, vodka) -> {
			log.debug("Waiting for my drinks...");
			log.debug("Got my order! Thank you lad! " + asList(beer, vodka));
			return "drunk";
		});


		// https://www.youtube.com/watch?v=IwJ-SCfXoAU
	}
}

@Slf4j
@Service
class Barman {
	@Async
	public CompletableFuture<Beer> pourBeer() {
		 log.debug("Pouring Beer...");
		 sleep(1000);
		 return completedFuture(new Beer());
	 }

	@Async
	 public CompletableFuture<Vodka> pourVodka() {
		 log.debug("Pouring Vodka...");
		 sleep(1000);
		 return completedFuture(new Vodka());
	 }
}

@Data
class Beer {
}

@Data
class Vodka {
}
