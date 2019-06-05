package victor.training.oo.behavioral.command;

import java.util.Arrays;
import java.util.concurrent.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import victor.training.oo.stuff.ThreadUtils;

import static java.util.concurrent.Executors.*;

@EnableAsync
@SpringBootApplication
public class CommandSpringApp {
	public static void main(String[] args) {
		SpringApplication.run(CommandSpringApp.class, args).close(); // Note: .close to stop executors after CLRunner finishes
	}

	@Bean
	public ThreadPoolTaskExecutor executor(@Value("${barman.count:2}") int threadCount) {
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

	// TODO [1] inject and use a ThreadPoolTaskExecutor.submit
	// TODO [2] make them return a CompletableFuture + @Async + asyncExecutor bean
	public void run(String... args) throws Exception {
		log.debug("Submitting my order");

		Future<Ale> futureAle = barman.getOneAle();
		Future<Whiskey> futureWhiskey = barman.getOneWhiskey();
		log.debug("The waitress left with my order");
		Ale ale = futureAle.get();
		Whiskey whiskey = futureWhiskey.get();
		log.debug("Got my order! Thank you lad! " + Arrays.asList(ale, whiskey));

		barman.curse("***E@!#^#&@!#&!");
		log.debug("I go home");
	}
}

@Slf4j
@Service
class Barman {
	@Async
	public Future<Ale> getOneAle() {
		 ThreadUtils.sleep(1000);
		 log.debug("Pouring Ale...");
		 return CompletableFuture.completedFuture(new Ale());
	 }
	@Async
	 public Future<Whiskey> getOneWhiskey() {
		 ThreadUtils.sleep(1000);
		 log.debug("Pouring Whiskey...");
		 return CompletableFuture.completedFuture(new Whiskey());
	 }

	 @Async
	public void curse(String s) {
		throw new IllegalArgumentException("I'll kill you!");
	}
}

@Data
class Ale {
}

@Data
class Whiskey {
}
