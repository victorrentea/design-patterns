package victor.training.oo.behavioral.command;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.commons.lang.StringUtils;
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

@EnableAsync
@SpringBootApplication
public class CommandSpringApp {
	public static void main(String[] args) {
		SpringApplication.run(CommandSpringApp.class, args).close(); // Note: .close to stop executors after CLRunner finishes
	}

	@Bean
	public ThreadPoolTaskExecutor barmanExecutor(@Value("${barman.thread.pool.size:2}") int poolSize) {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(poolSize);
		executor.setMaxPoolSize(poolSize);
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
		log.debug("Submitting my order to: " + barman.getClass());
		Future<Ale> futureAle = barman.getOneAle();
		Future<Whiskey> futureWhiskey = barman.getOneWhiskey();
		log.debug("A plecat fata cu comanda");
		Ale ale = futureAle.get();

		Whiskey whiskey = futureWhiskey.get();
		log.debug("Got my order! Thank you lad! " + Arrays.asList(ale, whiskey));

        Future<Void> voidFuture = barman.injura_l("^#%!^%#^@!#^%#^@%#^@#%");
        voidFuture.get();
        log.debug("Plec linistit acasa");

	}
}

@Slf4j
@Service
class Barman {
	@Async("barmanExecutor")
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
	public Future<Void> injura_l(String argou) {
		if (StringUtils.isNotEmpty(argou)) {
		    throw new RuntimeException("Te casez!");
        }
		return null;
	 }
}

@Data
class Ale {
}

@Data
class Whiskey {
}
