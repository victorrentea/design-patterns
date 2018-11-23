package victor.training.oo.behavioral.command;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import org.jooq.lambda.Unchecked;
import org.springframework.beans.factory.annotation.Autowired;
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

@EnableAsync // SOLUTION
@SpringBootApplication
public class CommandSpringApp {
	public static void main(String[] args) {
		SpringApplication.run(CommandSpringApp.class, args).close(); // Note: .close to stop executors after CLRunner finishes
	}

	@Bean
	public ThreadPoolTaskExecutor executor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
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
	
	// [1] inject and use a ThreadPoolTaskExecutor.submit
	// TODO [2] make them return a CompletableFuture + @Async + asyncExecutor bean
	public void run(String... args) throws Exception {
		log.debug("Submitting my order");
		System.out.println("Cine-oi fi tu mah? " + barman.getClass());
		Future<Ale> viitoareAle = barman.getOneAle();
		Future<Wiskey> viitoareWiskey = barman.getOneWiskey();
		log.debug("O plecat cu comanda mea");
		Ale ale = viitoareAle.get();
		Wiskey wiskey = viitoareWiskey.get();
		log.debug("Got my order! Thank you lad! " + Arrays.asList(ale, wiskey));
	}
}

@Slf4j
@Service
class Barman {
	@Async
	public CompletableFuture<Ale> getOneAle() {
		 log.debug("Pouring Ale...");
		 ThreadUtils.sleep(1000);
		 throw new IllegalStateException("Nu mai am Ale!!$!$&@!*$&@!#");
//		 return CompletableFuture.completedFuture(new Ale());
	 }
	 @Async
	 public CompletableFuture<Wiskey> getOneWiskey() {
		 log.debug("Pouring Wiskey...");
		 ThreadUtils.sleep(1000);
		 return CompletableFuture.completedFuture(new Wiskey());
	 }
}

@Data
class Ale {
}

@Data
class Wiskey {
}
