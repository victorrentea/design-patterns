package victor.training.oo.behavioral.command;

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

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import victor.training.oo.stuff.ThreadUtils;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@EnableAsync
@SpringBootApplication
@EnableBinding({Sink.class, Source.class})
public class CommandSpringApp {
	public static void main(String[] args) {
		SpringApplication.run(CommandSpringApp.class, args).close(); // Note: .close to stop executors after CLRunner finishes
	}

	@Bean
	public ThreadPoolTaskExecutor executor(@Value("${barman.count:2}")int threadCount) {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(threadCount);
		executor.setMaxPoolSize(threadCount);
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
	ThreadPoolTaskExecutor pool;

	// TODO [1] inject and use a ThreadPoolTaskExecutor.submit
	// TODO [2] make them return a CompletableFuture + @Async + asyncExecutor bean
    // TODO [3] wanna try it out over JMS? try out ServiceActivatorPattern
	public void run(String... args) throws Exception {
		Thread.sleep(3000);
		log.debug("Submitting my order");
		CompletableFuture<Whiskey> futureWhiskey = barman.getOneWhiskey();
		CompletableFuture<Ale> futureAle = barman.getOneAle();
		log.debug("A plecat fata cu comanda");

		CompletableFuture.allOf(futureAle, futureWhiskey)
				.thenRun(()->{});


		Whiskey whiskey = futureWhiskey.get();
		Ale ale = futureAle.get();
		log.debug("Got my order! Thank you lad! " + Arrays.asList(ale, whiskey));
	}
}

@Slf4j
@Service
class Barman {
	@Async
	public CompletableFuture<Ale> getOneAle() {
		 log.debug("Pouring Ale...");
		 ThreadUtils.sleep(1000);
		 return CompletableFuture.completedFuture(new Ale());
	 }
	@Async
	 public CompletableFuture<Whiskey> getOneWhiskey() {
		 log.debug("Pouring Whiskey...");
		 ThreadUtils.sleep(1000);
		 return CompletableFuture.completedFuture(new Whiskey());
	 }
}

@Data
class Ale {
}

@Data
class Whiskey {
}
