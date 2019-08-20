package victor.training.oo.behavioral.command;

import java.util.Arrays;
import java.util.concurrent.*;
import java.util.function.Supplier;

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

import static java.util.concurrent.CompletableFuture.allOf;
import static java.util.concurrent.CompletableFuture.supplyAsync;

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
	public void run(String... args) throws Exception {
		log.debug("Submitting my order");


		String countStr = System.getProperty("barman.thread.count", "2");
		int count = Integer.parseInt(countStr);

//        Runnable r = () -> barman.getOneAle();
//        Callable<Ale> c = () -> barman.getOneAle();


        CompletableFuture<Ale> futureAle = supplyAsync(() -> barman.getOneAle());
        CompletableFuture<Whiskey> futureWhiskey = supplyAsync(() -> barman.getOneWhiskey());

        futureAle.thenRun(() -> log.debug("Ale ready"));



//        allOf(futureAle, futureWhiskey).thenRun(() -> log.debug("Both drinks ready"));

		CompletableFuture<Cocktail> futureCocktail = futureAle.thenCombine(futureWhiskey,
				(ale, whiskey) -> {
					log.debug("Making a cocktail with {} and {} ", ale, whiskey);
					return new Cocktail(ale, whiskey);
				});
		futureCocktail.thenAccept(cocktail -> log.debug("Enjoying my {}", cocktail));
//		log.debug("Got my order! Thank you lad! " + Arrays.asList(ale, whiskey));


		log.debug("The waitress left with my order");
		ThreadUtils.sleep( 2000); // keep main alive. CPR
//		Ale ale = futureAle.get();
//		Whiskey whiskey = futureWhiskey.get();
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
class Cocktail {
	private final Ale ale;
	private final Whiskey whiskey;
}

@Data
class Ale {
}

@Data
class Whiskey {
}
