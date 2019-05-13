package victor.training.oo.behavioral.command;

import static java.util.concurrent.CompletableFuture.completedFuture;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncConfigurer;
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
	public ThreadPoolTaskExecutor executor(@Value("${thread.count:2}") int threadCount) {
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
		Future<Wiskey> futureWiskey = barman.getOneWiskey();
		
		Ale ale = futureAle.get();
		Wiskey wiskey = futureWiskey.get();
		Future<Void> viitorNimic = barman.trimiteiSms("te quiero");
		
		viitorNimic.get();
		log.debug("Got my order! Thank you lad! " + Arrays.asList(ale, wiskey));
	}
}

@Slf4j
@Service
class Barman {
	@Async
	public Future<Ale> getOneAle() {
		 log.debug("Pouring Ale...");
		 ThreadUtils.sleep(1000);
		 return completedFuture(new Ale());
	 }
	@Async
	public Future<Void> trimiteiSms(String string) {
//		 if (true) throw new RuntimeException("Te omor");
		 return completedFuture(null);
	}
	@Async
	 public Future<Wiskey> getOneWiskey() {
		 log.debug("Pouring Wiskey...");
		 ThreadUtils.sleep(1000);
		 return completedFuture(new Wiskey());
	 }
}

@Data
class Ale {
}

@Data
class Wiskey {
}
