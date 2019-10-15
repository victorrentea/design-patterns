package victor.training.oo.behavioral.command;

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

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import victor.training.oo.stuff.ThreadUtils;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static java.util.Arrays.asList;

@EnableAsync
@SpringBootApplication
@EnableBinding({Sink.class, Source.class})
public class CommandSpringApp {
	public static void main(String[] args) {
		SpringApplication.run(CommandSpringApp.class, args).close(); // Note: .close to stop executors after CLRunner finishes
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
@Component
class Drinker implements CommandLineRunner {
	@Autowired
	private Barman barman;

	// TODO [1] inject and use a ThreadPoolTaskExecutor.submit
	// TODO [2] make them return a CompletableFuture + @Async + asyncExecutor bean
    // TODO [3] wanna try it out over JMS? try out ServiceActivatorPattern
	public void run(String... args) throws InterruptedException, ExecutionException {
		log.debug("Submitting my order");
//		Ale ale = barman.getOneAle();
//		Whiskey whiskey = barman.getOneWhiskey();
		
		
		ExecutorService pool = Executors.newFixedThreadPool(2);
		
		Callable<Ale> comandaDeBere = barman::getOneAle;
		Future<Ale> futureAle = pool.submit(comandaDeBere);
		Future<Whiskey> futureWhiskey = pool.submit(barman::getOneWhiskey);

		log.debug("A plecat fata cu comanda"); // cade curentul aici
		Ale ale = futureAle.get();
		Whiskey whiskey = futureWhiskey.get();
		
		log.debug("Got my order! Thank you lad! " + asList(ale, whiskey));
	}
}

@Slf4j
@Service
class Barman {
	public Ale getOneAle() {
		 log.debug("Pouring Ale...");
		 ThreadUtils.sleep(1000);
		 return new Ale();
	 }
	
	 public Whiskey getOneWhiskey() {
		 log.debug("Pouring Whiskey...");
		 ThreadUtils.sleep(1000);
		 return new Whiskey();
	 }
}

@Data
class Ale {
}

@Data
class Whiskey {
}
