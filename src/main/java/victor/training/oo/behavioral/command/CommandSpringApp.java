package victor.training.oo.behavioral.command;

import lombok.Data;
import lombok.ToString;
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

import javax.annotation.PostConstruct;
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

@Service
class A {
	@Autowired
	public B b;
}
@Service
class B {
	@Autowired
	public C c;
}
@Service
class C {
	@Autowired
	public A a;
	@PostConstruct
	public void init() {
		System.out.println(a);
//		System.out.println(a.b.c == this);
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
	public void run(String... args) throws ExecutionException, InterruptedException {
		log.debug("Submitting my order to : " + barman.getClass());

		CompletableFuture<Beer> futureBere = barman.pourBeer();
		CompletableFuture<Vodka> futureVodka = barman.pourVodka();
//		barman.pourBeer();
//		barman.pourVodka();
//		barman.pourBeer();
//		barman.pourVodka();
//		barman.pourBeer();
//		barman.pourVodka();
//		barman.pourBeer();
//		barman.pourVodka();
//		barman.pourBeer();
//		barman.pourVodka();
		log.debug("A plecat fata cu ambele comenzi");

		CompletableFuture<DillyDilly> futureDilly
			= futureBere.thenCombine(futureVodka, (b, v) -> new DillyDilly(b, v));

		futureDilly.thenAccept(dilly -> bea(dilly));

		log.debug("main() pleaca acasa");
	}

	private void bea(DillyDilly dilly) {
		log.debug("Got my order! Thank you lad! " + dilly);
	}
}

@ToString
class DillyDilly {
	private final Beer beer;
	private final Vodka vodka;

	DillyDilly(Beer beer, Vodka vodka) {
		this.beer = beer;
		this.vodka = vodka;
		sleep(1000);
	}
}

@Slf4j
@Service
class Barman {
	@Async
	public CompletableFuture<Beer> pourBeer() {
		 log.debug("Pouring Beer...");
		 sleep(1500); // pute a REST call, sau READ FILE MARE, CRIPTEAZ-O PASTA,
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
