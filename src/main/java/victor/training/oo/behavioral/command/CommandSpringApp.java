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
import victor.training.oo.stuff.ConcurrencyUtil;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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

	// TODO [1] inject and use a ThreadPoolTaskExecutor.submit
	// TODO [2] make them return a CompletableFuture + @Async + asyncExecutor bean
    // TODO [3] wanna try it out over JMS? try out ServiceActivatorPattern
	public void run(String... args) throws ExecutionException, InterruptedException {
		log.debug("Submitting my order to " + barman.getClass());

//		ExecutorService pool = Executors.newFixedThreadPool(2);

		// acest apel merge in Proxy-ul injectat in campul Barman. Proxy-ul doar va 'submite' cererea de bere intr-un tread pool ascuns
		// proxy-ul iti returneaza o instanta de "command object" Future
		CompletableFuture<Beer> futureBeer = barman.pourBeer();
		CompletableFuture<Vodka> futureVodka = barman.pourVodka();

		log.debug("A plecat fata cu comanda");
		log.debug("Bat darabana...");
		CompletableFuture<UBoat> futureUBoat = futureBeer
			.thenCombine(futureVodka, (b, v) -> new UBoat(b, v));

		log.debug("Waiting for my drinks...");

		futureUBoat.thenAccept(uboat ->
			log.debug("Got my order! Thank you lad! " + uboat));
		log.debug("Plec acasa");
	}
}

@Slf4j
@Data
class UBoat {
	private final Beer beer;
	private final Vodka vodka;

	public UBoat(Beer beer, Vodka vodka) {
		this.beer = beer;
		this.vodka = vodka;
		log.debug("Amestec ");
		ConcurrencyUtil.sleep2(1000);
	}
}

@Slf4j
@Service
class Barman {
	@Async("executor")
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
